package models

import monzo_scala.models._
import org.specs2.mutable.Specification

class AccountsModelsSpec extends Specification {
  "AccountsList Model" should {
    "Construct from json" in {
      val accounts = AccountsList.fromJson(ExampleJson.accountsJson)

      accounts mustNotEqual null
      accounts.accounts.length must equalTo(1)
      accounts.accounts.head.id must equalTo("acc_00009237aqC8c5umZmrRdh")
      accounts.accounts.head.description must equalTo("Peter Pan's Account")
      accounts.accounts.head.created must equalTo("2015-11-13T12:17:42Z")
    }

    "Convert to json" in {
      val accounts = AccountsList(
        Seq(
          Account(
            "acc_00009237aqC8c5umZmrRdh",
            "Peter Pan's Account",
            "2015-11-13T12:17:42Z")))

      accounts.toJson() must equalTo(ExampleJson.accountsJson)
    }
  }
}
