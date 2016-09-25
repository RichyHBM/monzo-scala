package monzo_scala.http

import monzo_scala.Enums
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaj.http._

case class ScalaJHttpClient() extends HttpClient {
  override def makeRequest(method: monzo_scala.Enums.Methods.Value,
                           url: String,
                           headers: Map[String, String],
                           form: Map[String, String]): Future[String] = {

    Future {
      val request = Http(url)

      val requestWithHeaders = headers.foldLeft(request)(
        (request, header) => request.header(header._1, header._2)
      )

      val requestWithData = method match {
        case Enums.Methods.Get => requestWithHeaders.method("GET")
        case Enums.Methods.Post => requestWithHeaders.postForm(form.toSeq).method("POST")
        case Enums.Methods.Put => requestWithHeaders.postForm(form.toSeq).method("PUT")
        case Enums.Methods.Patch => requestWithHeaders.postForm(form.toSeq).method("PATCH")
        case Enums.Methods.Delete => requestWithHeaders.method("DELETE")
      }

      val response = requestWithData.asString

      response.code match {
        case 200 => response.body
        case _ => throw ApiException(response.code, response.body)
      }
    }
  }
}
