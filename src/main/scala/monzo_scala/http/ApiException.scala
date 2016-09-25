package monzo_scala.http

case class ApiException(code: Int, message: String) extends Exception(s"$code: $message")
