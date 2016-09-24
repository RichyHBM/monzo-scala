package monzo_scala.models.webhooks

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class Webhook(accountId: String,
                   id: String,
                   url: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Webhook {
  def fromJson(json: String): Webhook = Json.parse(json).as[Webhook]

  implicit val reads: Reads[Webhook] = (
    (JsPath \ "account_id").read[String] and
      (JsPath \ "id").read[String] and
      (JsPath \ "url").read[String]
    ) (Webhook.apply _)

  implicit val writes: Writes[Webhook] = (
    (JsPath \ "account_id").write[String] and
      (JsPath \ "id").write[String] and
      (JsPath \ "url").write[String]
    ) (unlift(Webhook.unapply))
}
