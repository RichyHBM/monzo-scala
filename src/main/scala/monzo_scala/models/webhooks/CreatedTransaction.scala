package monzo_scala.models.webhooks

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class CreatedTransaction(transactionType: String, data: Data) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object CreatedTransaction {
  def fromJson(json: String): CreatedTransaction = Json.parse(json).as[CreatedTransaction]

  implicit val reads: Reads[CreatedTransaction] = (
    (JsPath \ "type").read[String] and
      (JsPath \ "data").read[Data]
    ) (CreatedTransaction.apply _)

  implicit val writes: Writes[CreatedTransaction] = (
    (JsPath \ "type").write[String] and
      (JsPath \ "data").write[Data]
    ) (unlift(CreatedTransaction.unapply))
}


