package monzo_scala.models.attachments

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}


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
