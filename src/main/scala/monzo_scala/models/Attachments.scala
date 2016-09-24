package monzo_scala.models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class UploadAttachment(fileUrl: String,
                            uploadUrl: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object UploadAttachment {
  def fromJson(json: String): UploadAttachment = Json.parse(json).as[UploadAttachment]

  implicit val reads: Reads[UploadAttachment] = (
    (JsPath \ "file_url").read[String] and
      (JsPath \ "upload_url").read[String]
    ) (UploadAttachment.apply _)

  implicit val writes: Writes[UploadAttachment] = (
    (JsPath \ "file_url").write[String] and
      (JsPath \ "upload_url").write[String]
    ) (unlift(UploadAttachment.unapply))
}


case class RegisterAttachment(attachment: Attachment) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object RegisterAttachment {
  def fromJson(json: String): RegisterAttachment = Json.parse(json).as[RegisterAttachment]

  implicit val reads: Reads[RegisterAttachment] = (JsPath \ "attachment").read[Attachment].map(RegisterAttachment(_))
  implicit val writes: Writes[RegisterAttachment] = (JsPath \ "attachment").write[Attachment].contramap[RegisterAttachment](_.attachment)
}

case class DeregisterAttachment() extends JsonModel {
  override def toJson(): String = "{}"
}

object DeregisterAttachment {
  def fromJson(json: String): DeregisterAttachment = Json.parse(json).as[DeregisterAttachment]

  implicit val reads: Reads[DeregisterAttachment] = Reads {
    _.validate[JsObject] map (_ => DeregisterAttachment())
  }
}

case class Attachment(id: String,
                      userId: String,
                      externalId: String,
                      fileUrl: String,
                      fileType: String,
                      created: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Attachment {
  def fromJson(json: String): Attachment = Json.parse(json).as[Attachment]

  implicit val reads: Reads[Attachment] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "user_id").read[String] and
      (JsPath \ "external_id").read[String] and
      (JsPath \ "file_url").read[String] and
      (JsPath \ "file_type").read[String] and
      (JsPath \ "created").read[String]
    ) (Attachment.apply _)

  implicit val writes: Writes[Attachment] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "user_id").write[String] and
      (JsPath \ "external_id").write[String] and
      (JsPath \ "file_url").write[String] and
      (JsPath \ "file_type").write[String] and
      (JsPath \ "created").write[String]
    ) (unlift(Attachment.unapply))
}