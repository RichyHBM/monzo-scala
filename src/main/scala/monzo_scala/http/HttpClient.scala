package monzo_scala.http

import monzo_scala.Enums

import scala.concurrent.Future

trait HttpClient {
  def makeRequest(method: Enums.Methods.Value,
                  url: String,
                  headers: Map[String, String],
                  form: Map[String, String]) : Future[String]
}
