package monzo_scala.models.transactions

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class Merchant(address: Address,
                    created: String,
                    groupId: String,
                    id: String,
                    logo: String,
                    emoji: String,
                    name: String,
                    category: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Merchant {
  def fromJson(json: String): Merchant = Json.parse(json).as[Merchant]

  implicit val reads: Reads[Merchant] = (
    (JsPath \ "address").read[Address] and
      (JsPath \ "created").read[String] and
      (JsPath \ "group_id").read[String] and
      (JsPath \ "id").read[String] and
      (JsPath \ "logo").read[String] and
      (JsPath \ "emoji").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "category").read[String]
    ) (Merchant.apply _)

  implicit val writes: Writes[Merchant] = (
    (JsPath \ "address").write[Address] and
      (JsPath \ "created").write[String] and
      (JsPath \ "group_id").write[String] and
      (JsPath \ "id").write[String] and
      (JsPath \ "logo").write[String] and
      (JsPath \ "emoji").write[String] and
      (JsPath \ "name").write[String] and
      (JsPath \ "category").write[String]
    ) (unlift(Merchant.unapply))
}