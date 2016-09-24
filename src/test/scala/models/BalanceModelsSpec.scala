package models

import monzo_scala.models._
import org.specs2.mutable.Specification
import play.api.libs.json.Json

class BalanceModelsSpec extends Specification {
  val balanceJson = Json.stringify(Json.parse(
    """{
      |    "balance": 5000,
      |    "currency": "GBP",
      |    "spend_today": 0
      |}""".stripMargin))

  "Balance Model" should {
    "Construct from json" in {
      val balance = Balance.fromJson(balanceJson)

      balance mustNotEqual null
      balance.balance must equalTo(5000)
      balance.currency must equalTo("GBP")
      balance.spendToday must equalTo(0)
    }

    "Convert to json" in {
      val balance = Balance(5000, "GBP", 0)

      balance.toJson() must equalTo(balanceJson)
    }
  }
}
