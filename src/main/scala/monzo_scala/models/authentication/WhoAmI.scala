package monzo_scala.models.authentication

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class WhoAmI(authenticated: Boolean,
                  clientId: String,
                  userId: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
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