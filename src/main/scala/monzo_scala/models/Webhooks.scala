package monzo_scala.models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class RegisterWebhook(webhook: Webhook) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object RegisterWebhook {
  def fromJson(json: String): RegisterWebhook = Json.parse(json).as[RegisterWebhook]

  implicit val reads: Reads[RegisterWebhook] = (JsPath \ "webhook").read[Webhook].map(RegisterWebhook(_))
  implicit val writes: Writes[RegisterWebhook] = (JsPath \ "webhook").write[Webhook].contramap[RegisterWebhook](_.webhook)
}

case class ListWebhooks(webhooks: Seq[Webhook]) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object ListWebhooks {
  def fromJson(json: String): ListWebhooks = Json.parse(json).as[ListWebhooks]

  implicit val reads: Reads[ListWebhooks] = (JsPath \ "webhooks").read[Seq[Webhook]].map(ListWebhooks(_))
  implicit val writes: Writes[ListWebhooks] = (JsPath \ "webhooks").write[Seq[Webhook]].contramap[ListWebhooks](_.webhooks)
}

case class DeleteWebhook() extends JsonModel {
  override def toJson(): String = "{}"
}

object DeleteWebhook {
  def fromJson(json: String): DeleteWebhook = Json.parse(json).as[DeleteWebhook]

  implicit val reads: Reads[DeleteWebhook] = Reads {
    _.validate[JsObject] map (_ => DeleteWebhook())
  }
}

case class Webhook(accountId: String,
                   id: String,
                   url: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Webhook {
  def fromJson(json: String): Webhook = Json.parse(json).as[Webhook]

  implicit val reads: Reads[Webhook] = (
    (JsPath \ "account_id").read[String] and
      (JsPath \ "id").read[String] and
      (JsPath \ "url").read[String]
    ) (Webhook.apply _)

  implicit val writes: Writes[Webhook] = (
    (JsPath \ "account_id").write[String] and
      (JsPath \ "id").write[String] and
      (JsPath \ "url").write[String]
    ) (unlift(Webhook.unapply))
}

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


