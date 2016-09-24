package monzo_scala.models.transactions

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class TransactionList(transactions: Seq[ExpandedTransaction]) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object TransactionList {
  def fromJson(json: String): TransactionList = Json.parse(json).as[TransactionList]

  implicit val reads: Reads[TransactionList] = (JsPath \ "transactions").read[Seq[ExpandedTransaction]].map(TransactionList(_))
  implicit val writes: Writes[TransactionList] = (JsPath \ "transactions").write[Seq[ExpandedTransaction]].contramap[TransactionList](_.transactions)
}