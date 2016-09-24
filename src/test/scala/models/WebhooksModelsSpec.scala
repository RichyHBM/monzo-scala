package models

import monzo_scala.models.transactions.{Address, Merchant}
import monzo_scala.models.webhooks._
import org.specs2.mutable.Specification

class WebhooksModelsSpec extends Specification {
  "RetrieveWebhook Model" should {
    "Construct from json" in {
      val registerWebhook = RegisterWebhook.fromJson(ExampleJson.registerWebhookJson)

      registerWebhook mustNotEqual null
      registerWebhook.webhook mustNotEqual null
      registerWebhook.webhook.accountId must equalTo("account_id")
      registerWebhook.webhook.id must equalTo("webhook_id")
      registerWebhook.webhook.url must equalTo("http://example.com")
    }

    "Convert to json" in {
      val registerWebhook = RegisterWebhook(Webhook("account_id", "webhook_id", "http://example.com"))

      registerWebhook.toJson() must equalTo(ExampleJson.registerWebhookJson)
    }
  }

  "ListWebhooks Model" should {
    "Construct from json" in {
      val webhookList = WebhookList.fromJson(ExampleJson.webhookListJson)

      webhookList mustNotEqual null
      webhookList.webhooks.length must equalTo(2)

      webhookList.webhooks.head.accountId must equalTo("acc_000091yf79yMwNaZHhHGzp")
      webhookList.webhooks.head.id must equalTo("webhook_000091yhhOmrXQaVZ1Irsv")
      webhookList.webhooks.head.url must equalTo("http://example.com/callback")

      webhookList.webhooks(1).accountId must equalTo("acc_000091yf79yMwNaZHhHGzp")
      webhookList.webhooks(1).id must equalTo("webhook_000091yhhzvJSxLYGAceC9")
      webhookList.webhooks(1).url must equalTo("http://example2.com/anothercallback")
    }

    "Convert to json" in {
      val webhookList = WebhookList(Seq(
        Webhook("acc_000091yf79yMwNaZHhHGzp", "webhook_000091yhhOmrXQaVZ1Irsv", "http://example.com/callback"),
        Webhook("acc_000091yf79yMwNaZHhHGzp", "webhook_000091yhhzvJSxLYGAceC9", "http://example2.com/anothercallback")
      ))

      webhookList.toJson() must equalTo(ExampleJson.webhookListJson)
    }
  }

  "DeleteWebhook Model" should {
    "Construct from json" in {
      val deleteWebhook = DeleteWebhook.fromJson(ExampleJson.deleteWebhookJson)

      deleteWebhook mustNotEqual null
    }

    "Convert to json" in {
      val deleteWebhook = DeleteWebhook()

      deleteWebhook.toJson() must equalTo(ExampleJson.deleteWebhookJson)
    }
  }

  "CreatedTransaction Model" should {
    "Construct from json" in {
      val createdTransaction = CreatedTransaction.fromJson(ExampleJson.createdTransactionJson)

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

      createdTransaction.toJson() must equalTo(ExampleJson.createdTransactionJson)
    }
  }
}