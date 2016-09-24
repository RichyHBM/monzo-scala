package monzo_scala.models.attachments

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class RegisterAttachment(attachment: Attachment) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object RegisterAttachment {
  def fromJson(json: String): RegisterAttachment = Json.parse(json).as[RegisterAttachment]

  implicit val reads: Reads[RegisterAttachment] = (JsPath \ "attachment").read[Attachment].map(RegisterAttachment(_))
  implicit val writes: Writes[RegisterAttachment] = (JsPath \ "attachment").write[Attachment].contramap[RegisterAttachment](_.attachment)
}