package monzo_scala.models.webhooks

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class RegisterWebhook(webhook: Webhook) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object RegisterWebhook {
  def fromJson(json: String): RegisterWebhook = Json.parse(json).as[RegisterWebhook]

  implicit val reads: Reads[RegisterWebhook] = (JsPath \ "webhook").read[Webhook].map(RegisterWebhook(_))
  implicit val writes: Writes[RegisterWebhook] = (JsPath \ "webhook").write[Webhook].contramap[RegisterWebhook](_.webhook)
}
