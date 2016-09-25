package monzo_scala.models.transactions

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class ExpandedTransaction(accountBalance: Long,
                               amount: Long,
                               created: String,
                               currency: String,
                               description: String,
                               id: String,
                               merchant: Merchant,
                               metadata: Map[String, String],
                               notes: String,
                               isLoad: Boolean,
                               settled: String,
                               category: String,
                               declinedReason: Option[String]) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object ExpandedTransaction {
  def fromJson(json: String): ExpandedTransaction = Json.parse(json).as[ExpandedTransaction]

  implicit val reads: Reads[ExpandedTransaction] = (
    (JsPath \ "account_balance").read[Long] and
      (JsPath \ "amount").read[Long] and
      (JsPath \ "created").read[String] and
      (JsPath \ "currency").read[String] and
      (JsPath \ "description").read[String] and
      (JsPath \ "id").read[String] and
      (JsPath \ "merchant").read[Merchant] and
      (JsPath \ "metadata").read[Map[String, String]] and
      (JsPath \ "notes").read[String] and
      (JsPath \ "is_load").read[Boolean] and
      (JsPath \ "settled").read[String] and
      (JsPath \ "category").read[String] and
      (JsPath \ "decline_reason").readNullable[String]
    ) (ExpandedTransaction.apply _)

  implicit val writes: Writes[ExpandedTransaction] = (
    (JsPath \ "account_balance").write[Long] and
      (JsPath \ "amount").write[Long] and
      (JsPath \ "created").write[String] and
      (JsPath \ "currency").write[String] and
      (JsPath \ "description").write[String] and
      (JsPath \ "id").write[String] and
      (JsPath \ "merchant").write[Merchant] and
      (JsPath \ "metadata").write[Map[String, String]] and
      (JsPath \ "notes").write[String] and
      (JsPath \ "is_load").write[Boolean] and
      (JsPath \ "settled").write[String] and
      (JsPath \ "category").write[String] and
      (JsPath \ "decline_reason").writeNullable[String]
    ) (unlift(ExpandedTransaction.unapply))
}
