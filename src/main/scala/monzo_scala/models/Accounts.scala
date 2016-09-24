package monzo_scala.models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class AccountsList(accounts: Seq[Account]) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}

case class Account(id: String,
                   description: String,
                   created: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object AccountsList {
  def fromJson(json: String): AccountsList = Json.parse(json).as[AccountsList]

  implicit val reads: Reads[AccountsList] = (JsPath \ "accounts").read[Seq[Account]].map(AccountsList(_))
  implicit val writes: Writes[AccountsList] = (JsPath \ "accounts").write[Seq[Account]].contramap[AccountsList](_.accounts)
}

object Account {
  def fromJson(json: String): Account = Json.parse(json).as[Account]

  implicit val reads: Reads[Account] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "description").read[String] and
      (JsPath \ "created").read[String]
    ) (Account.apply _)

  implicit val writes: Writes[Account] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "description").write[String] and
      (JsPath \ "created").write[String]
    ) (unlift(Account.unapply))
}

