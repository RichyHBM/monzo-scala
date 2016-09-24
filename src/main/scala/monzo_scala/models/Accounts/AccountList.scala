package monzo_scala.models.accounts

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class AccountList(accounts: Seq[Account]) extends JsonModel {
  override def toJson(): String = Json.stringify(Json.toJson(this))
}


object AccountList {
  def fromJson(json: String): AccountList = Json.parse(json).as[AccountList]

  implicit val reads: Reads[AccountList] = (JsPath \ "accounts").read[Seq[Account]].map(AccountList(_))
  implicit val writes: Writes[AccountList] = (JsPath \ "accounts").write[Seq[Account]].contramap[AccountList](_.accounts)
}
