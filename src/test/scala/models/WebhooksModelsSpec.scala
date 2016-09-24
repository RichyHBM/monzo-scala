package models

import monzo_scala.models._
import org.specs2.mutable.Specification
import play.api.libs.json.Json

class WebhooksModelsSpec extends Specification {
  val registerWebhookJson = Json.stringify(Json.parse(
    """{
      |    "webhook": {
      |        "account_id": "account_id",
      |        "id": "webhook_id",
      |        "url": "http://example.com"
      |    }
      |}""".stripMargin))

  val listWebhookJson = Json.stringify(Json.parse(
    """{
      |    "webhooks": [
      |        {
      |            "account_id": "acc_000091yf79yMwNaZHhHGzp",
      |            "id": "webhook_000091yhhOmrXQaVZ1Irsv",
      |            "url": "http://example.com/callback"
      |        },
      |        {
      |            "account_id": "acc_000091yf79yMwNaZHhHGzp",
      |            "id": "webhook_000091yhhzvJSxLYGAceC9",
      |            "url": "http://example2.com/anothercallback"
      |        }
      |    ]
      |}""".stripMargin))

  val deleteWebhookJson = Json.stringify(Json.parse("""{}""".stripMargin))

  val createdTransactionJson = Json.stringify(Json.parse(
    """{
      |    "type": "transaction.created",
      |    "data": {
      |        "account_id": "acc_00008gju41AHyfLUzBUk8A",
      |        "amount": -350,
      |        "created": "2015-09-04T14:28:40Z",
      |        "currency": "GBP",
      |        "description": "Ozone Coffee Roasters",
      |        "id": "tx_00008zjky19HyFLAzlUk7t",
      |        "category": "eating_out",
      |        "is_load": false,
      |        "settled": true,
      |        "merchant": {
      |            "address": {
      |                "address": "98 Southgate Road",
      |                "city": "London",
      |                "country": "GB",
      |                "latitude": 51.54151,
      |                "longitude": -0.08482400000002599,
      |                "postcode": "N1 3JD",
      |                "region": "Greater London"
      |            },
      |            "created": "2015-08-22T12:20:18Z",
      |            "group_id": "grp_00008zIcpbBOaAr7TTP3sv",
      |            "id": "merch_00008zIcpbAKe8shBxXUtl",
      |            "logo": "https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg",
      |            "emoji": "🍞",
      |            "name": "The De Beauvoir Deli Co.",
      |            "category": "eating_out"
      |        }
      |    }
      |}""".stripMargin))

  "RetrieveWebhook Model" should {
    "Construct from json" in {
      val registerWebhook = RegisterWebhook.fromJson(registerWebhookJson)

      registerWebhook mustNotEqual null
      registerWebhook.webhook mustNotEqual null
      registerWebhook.webhook.accountId must equalTo("account_id")
      registerWebhook.webhook.id must equalTo("webhook_id")
      registerWebhook.webhook.url must equalTo("http://example.com")
    }

    "Convert to json" in {
      val registerWebhook = RegisterWebhook(Webhook("account_id", "webhook_id", "http://example.com"))

      registerWebhook.toJson() must equalTo(registerWebhookJson)
    }
  }

  "ListWebhooks Model" should {
    "Construct from json" in {
      val listWebhook = ListWebhooks.fromJson(listWebhookJson)

      listWebhook mustNotEqual null
      listWebhook.webhooks.length must equalTo(2)

      listWebhook.webhooks.head.accountId must equalTo("acc_000091yf79yMwNaZHhHGzp")
      listWebhook.webhooks.head.id must equalTo("webhook_000091yhhOmrXQaVZ1Irsv")
      listWebhook.webhooks.head.url must equalTo("http://example.com/callback")

      listWebhook.webhooks(1).accountId must equalTo("acc_000091yf79yMwNaZHhHGzp")
      listWebhook.webhooks(1).id must equalTo("webhook_000091yhhzvJSxLYGAceC9")
      listWebhook.webhooks(1).url must equalTo("http://example2.com/anothercallback")
    }

    "Convert to json" in {
      val listWebhook = ListWebhooks(Seq(
        Webhook("acc_000091yf79yMwNaZHhHGzp", "webhook_000091yhhOmrXQaVZ1Irsv", "http://example.com/callback"),
        Webhook("acc_000091yf79yMwNaZHhHGzp", "webhook_000091yhhzvJSxLYGAceC9", "http://example2.com/anothercallback")
      ))

      listWebhook.toJson() must equalTo(listWebhookJson)
    }
  }

  "DeleteWebhook Model" should {
    "Construct from json" in {
      val deleteWebhook = DeleteWebhook.fromJson(deleteWebhookJson)

      deleteWebhook mustNotEqual null
    }

    "Convert to json" in {
      val deleteWebhook = DeleteWebhook()

      deleteWebhook.toJson() must equalTo(deleteWebhookJson)
    }
  }

  "CreatedTransaction Model" should {
    "Construct from json" in {
      val createdTransaction = CreatedTransaction.fromJson(createdTransactionJson)

      createdTransaction mustNotEqual null
      createdTransaction.transactionType must equalTo("transaction.created")

      createdTransaction.data mustNotEqual null
      createdTransaction.data.accountId must equalTo("acc_00008gju41AHyfLUzBUk8A")
      createdTransaction.data.amount must equalTo(-350)
      createdTransaction.data.created must equalTo("2015-09-04T14:28:40Z")
      createdTransaction.data.currency must equalTo("GBP")
      createdTransaction.data.description must equalTo("Ozone Coffee Roasters")
      createdTransaction.data.id must equalTo("tx_00008zjky19HyFLAzlUk7t")
      createdTransaction.data.category must equalTo("eating_out")
      createdTransaction.data.isLoad must equalTo(false)
      createdTransaction.data.settled must equalTo(true)

      createdTransaction.data.merchant mustNotEqual null
      createdTransaction.data.merchant.address mustNotEqual null
      createdTransaction.data.merchant.address.address must equalTo("98 Southgate Road")
      createdTransaction.data.merchant.address.city must equalTo("London")
      createdTransaction.data.merchant.address.country must equalTo("GB")
      createdTransaction.data.merchant.address.latitude must equalTo(51.54151)
      createdTransaction.data.merchant.address.longitude must equalTo(-0.08482400000002599)
      createdTransaction.data.merchant.address.postCode must equalTo("N1 3JD")
      createdTransaction.data.merchant.address.region must equalTo("Greater London")

      createdTransaction.data.merchant.created must equalTo("2015-08-22T12:20:18Z")
      createdTransaction.data.merchant.groupId must equalTo("grp_00008zIcpbBOaAr7TTP3sv")
      createdTransaction.data.merchant.id must equalTo("merch_00008zIcpbAKe8shBxXUtl")
      createdTransaction.data.merchant.logo must equalTo("https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg")
      createdTransaction.data.merchant.emoji must equalTo("🍞")
      createdTransaction.data.merchant.name must equalTo("The De Beauvoir Deli Co.")
      createdTransaction.data.merchant.category must equalTo("eating_out")
    }

    "Convert to json" in {
      val createdTransaction = CreatedTransaction("transaction.created",
        Data("acc_00008gju41AHyfLUzBUk8A",
          -350,
          "2015-09-04T14:28:40Z",
          "GBP",
          "Ozone Coffee Roasters",
          "tx_00008zjky19HyFLAzlUk7t",
          "eating_out",
          false,
          true,
          Merchant(
            Address("98 Southgate Road", "London", "GB", 51.54151, -0.08482400000002599, "N1 3JD", "Greater London"),
            "2015-08-22T12:20:18Z",
            "grp_00008zIcpbBOaAr7TTP3sv",
            "merch_00008zIcpbAKe8shBxXUtl",
            "https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg",
            "🍞",
            "The De Beauvoir Deli Co.",
            "eating_out")
        )
      )

      createdTransaction.toJson() must equalTo(createdTransactionJson)
    }
  }
}