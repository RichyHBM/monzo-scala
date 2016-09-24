package monzo_scala.models.webhooks

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsObject, Json, Reads}

case class DeleteWebhook() extends JsonModel {
  override def toJson(): String = "{}"
}

object DeleteWebhook {
  def fromJson(json: String): DeleteWebhook = Json.parse(json).as[DeleteWebhook]

  implicit val reads: Reads[DeleteWebhook] = Reads {
    _.validate[JsObject] map (_ => DeleteWebhook())
  }
}
