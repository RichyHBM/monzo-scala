package monzo_scala.models.transactions

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class SingleTransaction(transaction: ExpandedTransaction) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object SingleTransaction {
  def fromJson(json: String): SingleTransaction = Json.parse(json).as[SingleTransaction]

  implicit val reads: Reads[SingleTransaction] = (JsPath \ "transaction").read[ExpandedTransaction].map(SingleTransaction(_))
  implicit val writes: Writes[SingleTransaction] = (JsPath \ "transaction").write[ExpandedTransaction].contramap[SingleTransaction](_.transaction)
}
