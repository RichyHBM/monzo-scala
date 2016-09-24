package monzo_scala.models.transactions

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class AnnotateTransaction(transaction: ExpandedTransaction) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object AnnotateTransaction {
  def fromJson(json: String): AnnotateTransaction = Json.parse(json).as[AnnotateTransaction]

  implicit val reads: Reads[AnnotateTransaction] = (JsPath \ "transaction").read[ExpandedTransaction].map(AnnotateTransaction(_))
  implicit val writes: Writes[AnnotateTransaction] = (JsPath \ "transaction").write[ExpandedTransaction].contramap[AnnotateTransaction](_.transaction)
}