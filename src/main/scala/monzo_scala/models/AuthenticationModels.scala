package monzo_scala.models

import play.api.libs.functional.syntax._
import play.api.libs.json.{Writes, JsPath, Reads, Json}

case class Access(accessToken: String,
                  clientId: String,
                  expiresIn: Long,
                  refreshToken: String,
                  tokenType: String,
                  userId: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

case class WhoAmI(authenticated: Boolean,
                  clientId: String,
                  userId: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Access {
  def fromJson(json: String): Access = Json.parse(json).as[Access]

  implicit val reads: Reads[Access] = (
    (JsPath \ "access_token").read[String] and
      (JsPath \ "client_id").read[String] and
      (JsPath \ "expires_in").read[Long] and
      (JsPath \ "refresh_token").read[String] and
      (JsPath \ "token_type").read[String] and
      (JsPath \ "user_id").read[String]
    ) (Access.apply _)

  implicit val writes: Writes[Access] = (
    (JsPath \ "access_token").write[String] and
      (JsPath \ "client_id").write[String] and
      (JsPath \ "expires_in").write[Long] and
      (JsPath \ "refresh_token").write[String] and
      (JsPath \ "token_type").write[String] and
      (JsPath \ "user_id").write[String]
    ) (unlift(Access.unapply))
}

object WhoAmI {
  def fromJson(json: String): WhoAmI = Json.parse(json).as[WhoAmI]

  implicit val reads: Reads[WhoAmI] = (
    (JsPath \ "authenticated").read[Boolean] and
      (JsPath \ "client_id").read[String] and
      (JsPath \ "user_id").read[String]
    ) (WhoAmI.apply _)

  implicit val writes: Writes[WhoAmI] = (
    (JsPath \ "authenticated").write[Boolean] and
      (JsPath \ "client_id").write[String] and
      (JsPath \ "user_id").write[String]
    ) (unlift(WhoAmI.unapply))
}
