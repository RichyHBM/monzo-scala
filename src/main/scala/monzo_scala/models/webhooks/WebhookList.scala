package monzo_scala.models.webhooks

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class WebhookList(webhooks: Seq[Webhook]) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object WebhookList {
  def fromJson(json: String): WebhookList = Json.parse(json).as[WebhookList]

  implicit val reads: Reads[WebhookList] = (JsPath \ "webhooks").read[Seq[Webhook]].map(WebhookList(_))
  implicit val writes: Writes[WebhookList] = (JsPath \ "webhooks").write[Seq[Webhook]].contramap[WebhookList](_.webhooks)
}
