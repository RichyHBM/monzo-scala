package monzo_scala.models.attachments

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsObject, Json, Reads}

case class DeregisterAttachment() extends JsonModel {
  override def toJson(): String = "{}"
}

object DeregisterAttachment {
  def fromJson(json: String): DeregisterAttachment = Json.parse(json).as[DeregisterAttachment]

  implicit val reads: Reads[DeregisterAttachment] = Reads {
    _.validate[JsObject] map (_ => DeregisterAttachment())
  }
}