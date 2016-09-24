package monzo_scala.models.attachments

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

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
