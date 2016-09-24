package monzo_scala.models

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

case class ListTransactions(transactions: Seq[ExpandedTransaction]) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object ListTransactions {
  def fromJson(json: String): ListTransactions = Json.parse(json).as[ListTransactions]

  implicit val reads: Reads[ListTransactions] = (JsPath \ "transactions").read[Seq[ExpandedTransaction]].map(ListTransactions(_))
  implicit val writes: Writes[ListTransactions] = (JsPath \ "transactions").write[Seq[ExpandedTransaction]].contramap[ListTransactions](_.transactions)
}

case class AnnotateTransactions(transaction: ExpandedTransaction) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object AnnotateTransactions {
  def fromJson(json: String): AnnotateTransactions = Json.parse(json).as[AnnotateTransactions]

  implicit val reads: Reads[AnnotateTransactions] = (JsPath \ "transaction").read[ExpandedTransaction].map(AnnotateTransactions(_))
  implicit val writes: Writes[AnnotateTransactions] = (JsPath \ "transaction").write[ExpandedTransaction].contramap[AnnotateTransactions](_.transaction)
}


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
                               category: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

case class Address(address: String,
                   city: String,
                   country: String,
                   latitude: Double,
                   longitude: Double,
                   postCode: String,
                   region: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

case class Merchant(address: Address,
                    created: String,
                    groupId: String,
                    id: String,
                    logo: String,
                    emoji: String,
                    name: String,
                    category: String) extends JsonModel {

  def this() {
    this(null, null, null, null, null, null, null, null)
  }

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
      (JsPath \ "category").read[String]
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
      (JsPath \ "category").write[String]
    ) (unlift(ExpandedTransaction.unapply))
}

object Address {
  def fromJson(json: String): Address = Json.parse(json).as[Address]

  implicit val reads: Reads[Address] = (
    (JsPath \ "address").read[String] and
      (JsPath \ "city").read[String] and
      (JsPath \ "country").read[String] and
      (JsPath \ "latitude").read[Double] and
      (JsPath \ "longitude").read[Double] and
      (JsPath \ "postcode").read[String] and
      (JsPath \ "region").read[String]
    ) (Address.apply _)

  implicit val writes: Writes[Address] = (
    (JsPath \ "address").write[String] and
      (JsPath \ "city").write[String] and
      (JsPath \ "country").write[String] and
      (JsPath \ "latitude").write[Double] and
      (JsPath \ "longitude").write[Double] and
      (JsPath \ "postcode").write[String] and
      (JsPath \ "region").write[String]
    ) (unlift(Address.unapply))
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