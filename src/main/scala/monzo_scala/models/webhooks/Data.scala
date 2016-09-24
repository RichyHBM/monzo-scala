package monzo_scala.models.webhooks

import monzo_scala.models.JsonModel
import monzo_scala.models.transactions.Merchant
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class Data(accountId: String,
                amount: Long,
                created: String,
                currency: String,
                description: String,
                id: String,
                category: String,
                isLoad: Boolean,
                settled: Boolean,
                merchant: Merchant) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Data {
  def fromJson(json: String): Data = Json.parse(json).as[Data]

  implicit val reads: Reads[Data] = (
    (JsPath \ "account_id").read[String] and
      (JsPath \ "amount").read[Long] and
      (JsPath \ "created").read[String] and
      (JsPath \ "currency").read[String] and
      (JsPath \ "description").read[String] and
      (JsPath \ "id").read[String] and
      (JsPath \ "category").read[String] and
      (JsPath \ "is_load").read[Boolean] and
      (JsPath \ "settled").read[Boolean] and
      (JsPath \ "merchant").read[Merchant]
    ) (Data.apply _)

  implicit val writes: Writes[Data] = (
    (JsPath \ "account_id").write[String] and
      (JsPath \ "amount").write[Long] and
      (JsPath \ "created").write[String] and
      (JsPath \ "currency").write[String] and
      (JsPath \ "description").write[String] and
      (JsPath \ "id").write[String] and
      (JsPath \ "category").write[String] and
      (JsPath \ "is_load").write[Boolean] and
      (JsPath \ "settled").write[Boolean] and
      (JsPath \ "merchant").write[Merchant]
    ) (unlift(Data.unapply))
}
