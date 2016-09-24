package monzo_scala.models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class Balance(balance: Long,
                   currency: String,
                   spendToday: Long) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Balance {
  def fromJson(json: String): Balance = Json.parse(json).as[Balance]

  implicit val reads: Reads[Balance] = (
    (JsPath \ "balance").read[Long] and
      (JsPath \ "currency").read[String] and
      (JsPath \ "spend_today").read[Long]
    ) (Balance.apply _)

  implicit val writes: Writes[Balance] = (
    (JsPath \ "balance").write[Long] and
      (JsPath \ "currency").write[String] and
      (JsPath \ "spend_today").write[Long]
    ) (unlift(Balance.unapply))
}