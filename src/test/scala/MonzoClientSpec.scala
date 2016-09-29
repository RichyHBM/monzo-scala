
import monzo_scala.MonzoClient
import monzo_scala.models._
import monzo_scala.models.transactions.{Address, Merchant}
import org.specs2.mutable.Specification

import scala.concurrent.duration._
import scala.concurrent.Await

class MonzoClientSpec extends Specification {
  val monzoClient = MonzoClient("", MockMonzoHttpClient())
  val merchant = Merchant(
    Address(
      "98 Southgate Road",
      "London",
      "GB",
      51.54151,
      -0.08482400000002599,
      "N1 3JD",
      "Greater London"),
    "2015-08-22T12:20:18Z",
    "grp_00008zIcpbBOaAr7TTP3sv",
    "merch_00008zIcpbAKe8shBxXUtl",
    "https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg",
    "🍞",
    "The De Beauvoir Deli Co.",
    "eating_out")

  "MonzoClient Mock" should {
    "Ping WhoAmI" in {
      val whoAmI = Await.result(monzoClient.whoAmI(), 5.seconds)

      whoAmI mustNotEqual null
      whoAmI.authenticated must equalTo(true)
      whoAmI.clientId must equalTo("client_id")
      whoAmI.userId must equalTo("user_id")
    }

    "List accounts" in {
      val accounts = Await.result(monzoClient.listAccounts(), 5.seconds)

      accounts mustNotEqual null
      accounts.accounts.length must equalTo(1)
      accounts.accounts.head.id must equalTo("acc_00009237aqC8c5umZmrRdh")
      accounts.accounts.head.description must equalTo("Peter Pan's Account")
      accounts.accounts.head.created must equalTo("2015-11-13T12:17:42Z")
    }

    "Get balance" in {
      val balance = Await.result(monzoClient.getBalance(""), 5.seconds)

      balance mustNotEqual null
      balance.balance must equalTo(5000)
      balance.currency must equalTo("GBP")
      balance.spendToday must equalTo(0)
    }

    "Get a transaction" in {
      val singleTransaction = Await.result(monzoClient.getTransaction("", true), 5.seconds)

      singleTransaction mustNotEqual null
      singleTransaction.transaction mustNotEqual null

      singleTransaction.transaction.accountBalance must equalTo(13013)
      singleTransaction.transaction.amount must equalTo(-510)
      singleTransaction.transaction.created must equalTo("2015-08-22T12:20:18Z")
      singleTransaction.transaction.currency must equalTo("GBP")
      singleTransaction.transaction.description must equalTo("THE DE BEAUVOIR DELI C LONDON        GBR")
      singleTransaction.transaction.id must equalTo("tx_00008zIcpb1TB4yeIFXMzx")

      singleTransaction.transaction.merchant mustNotEqual None
      singleTransaction.transaction.merchant.get.address mustNotEqual null
      singleTransaction.transaction.merchant.get.address.address must equalTo("98 Southgate Road")
      singleTransaction.transaction.merchant.get.address.city must equalTo("London")
      singleTransaction.transaction.merchant.get.address.country must equalTo("GB")
      singleTransaction.transaction.merchant.get.address.latitude must equalTo(51.54151)
      singleTransaction.transaction.merchant.get.address.longitude must equalTo(-0.08482400000002599)
      singleTransaction.transaction.merchant.get.address.postCode must equalTo("N1 3JD")
      singleTransaction.transaction.merchant.get.address.region must equalTo("Greater London")

      singleTransaction.transaction.merchant.get.created must equalTo("2015-08-22T12:20:18Z")
      singleTransaction.transaction.merchant.get.groupId must equalTo("grp_00008zIcpbBOaAr7TTP3sv")
      singleTransaction.transaction.merchant.get.id must equalTo("merch_00008zIcpbAKe8shBxXUtl")
      singleTransaction.transaction.merchant.get.logo must equalTo("https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg")
      singleTransaction.transaction.merchant.get.emoji must equalTo("🍞")
      singleTransaction.transaction.merchant.get.name must equalTo("The De Beauvoir Deli Co.")
      singleTransaction.transaction.merchant.get.category must equalTo("eating_out")

      singleTransaction.transaction.metadata must equalTo(Map[String, String]())
      singleTransaction.transaction.notes must equalTo("Salmon sandwich 🍞")
      singleTransaction.transaction.isLoad must equalTo(false)
      singleTransaction.transaction.settled must equalTo("2015-08-23T12:20:18Z")
      singleTransaction.transaction.category must equalTo("eating_out")
    }

    "List transactions" in {
      val transactionList = Await.result(monzoClient.listTransactions("", false, None, None, None), 5.seconds)

      transactionList mustNotEqual null
      transactionList.transactions.length must equalTo(2)

      transactionList.transactions.head.accountBalance must equalTo(13013)
      transactionList.transactions.head.amount must equalTo(-510)
      transactionList.transactions.head.created must equalTo("2015-08-22T12:20:18Z")
      transactionList.transactions.head.currency must equalTo("GBP")
      transactionList.transactions.head.description must equalTo("THE DE BEAUVOIR DELI C LONDON        GBR")
      transactionList.transactions.head.id must equalTo("tx_00008zIcpb1TB4yeIFXMzx")
      transactionList.transactions.head.merchant mustEqual None
      transactionList.transactions.head.metadata must equalTo(Map[String, String]())
      transactionList.transactions.head.notes must equalTo("Salmon sandwich 🍞")
      transactionList.transactions.head.isLoad must equalTo(false)
      transactionList.transactions.head.settled must equalTo("2015-08-23T12:20:18Z")
      transactionList.transactions.head.category must equalTo("eating_out")

      transactionList.transactions(1).accountBalance must equalTo(12334)
      transactionList.transactions(1).amount must equalTo(-679)
      transactionList.transactions(1).created must equalTo("2015-08-23T16:15:03Z")
      transactionList.transactions(1).currency must equalTo("GBP")
      transactionList.transactions(1).description must equalTo("VUE BSL LTD            ISLINGTON     GBR")
      transactionList.transactions(1).id must equalTo("tx_00008zL2INM3xZ41THuRF3")
      transactionList.transactions(1).merchant mustEqual None
      transactionList.transactions(1).metadata must equalTo(Map[String, String]())
      transactionList.transactions(1).notes must equalTo("")
      transactionList.transactions(1).isLoad must equalTo(false)
      transactionList.transactions(1).settled must equalTo("2015-08-24T16:15:03Z")
      transactionList.transactions(1).category must equalTo("eating_out")
    }

    "Annotate a transaction" in {
      val annotateTransaction = Await.result(monzoClient.annotateTransaction("", false, Map()), 5.seconds)

      annotateTransaction mustNotEqual null
      annotateTransaction.transaction mustNotEqual null

      annotateTransaction.transaction.accountBalance must equalTo(12334)
      annotateTransaction.transaction.amount must equalTo(-679)
      annotateTransaction.transaction.created must equalTo("2015-08-23T16:15:03Z")
      annotateTransaction.transaction.currency must equalTo("GBP")
      annotateTransaction.transaction.description must equalTo("VUE BSL LTD            ISLINGTON     GBR")
      annotateTransaction.transaction.id must equalTo("tx_00008zL2INM3xZ41THuRF3")
      annotateTransaction.transaction.merchant mustEqual None
      annotateTransaction.transaction.metadata must equalTo(Map[String, String]("foo" -> "bar"))
      annotateTransaction.transaction.notes must equalTo("")
      annotateTransaction.transaction.isLoad must equalTo(false)
      annotateTransaction.transaction.settled must equalTo("2015-08-24T16:15:03Z")
      annotateTransaction.transaction.category must equalTo("eating_out")
    }

    "Create a basic feed item" in {
      val basicFeedItem = Await.result(monzoClient.createBasicFeedItem("", None, "", "", None, None, None, None), 5.seconds)
      basicFeedItem mustNotEqual null
    }

    "Register a webhook" in {
      val registerWebhook = Await.result(monzoClient.registerWebhook("", ""), 5.seconds)

      registerWebhook mustNotEqual null
      registerWebhook.webhook mustNotEqual null
      registerWebhook.webhook.accountId must equalTo("account_id")
      registerWebhook.webhook.id must equalTo("webhook_id")
      registerWebhook.webhook.url must equalTo("http://example.com")
    }

    "List webhooks" in {
      val webhookList = Await.result(monzoClient.listWebhooks(""), 5.seconds)

      webhookList mustNotEqual null
      webhookList.webhooks.length must equalTo(2)

      webhookList.webhooks.head.accountId must equalTo("acc_000091yf79yMwNaZHhHGzp")
      webhookList.webhooks.head.id must equalTo("webhook_000091yhhOmrXQaVZ1Irsv")
      webhookList.webhooks.head.url must equalTo("http://example.com/callback")

      webhookList.webhooks(1).accountId must equalTo("acc_000091yf79yMwNaZHhHGzp")
      webhookList.webhooks(1).id must equalTo("webhook_000091yhhzvJSxLYGAceC9")
      webhookList.webhooks(1).url must equalTo("http://example2.com/anothercallback")
    }

    "Delete a webhook" in {
      val deleteWebhook = Await.result(monzoClient.deleteWebhook(""), 5.seconds)

      deleteWebhook mustNotEqual null
    }

    "Upload an attachment" in {
      val uploadAttachment = Await.result(monzoClient.uploadAttachment("", ""), 5.seconds)

      uploadAttachment mustNotEqual null
      uploadAttachment.fileUrl must equalTo("https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png")
      uploadAttachment.uploadUrl must equalTo("https://mondo-image-uploads.s3.amazonaws.com/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png?AWSAccessKeyId=AKIAIR3IFH6UCTCXB5PQ\u0026Expires=1447353431\u0026Signature=k2QeDCCQQHaZeynzYKckejqXRGU%!D(MISSING)")
    }

    "Register an attachment" in {
      val registerAttachment = Await.result(monzoClient.registerAttachment("", "", ""), 5.seconds)

      registerAttachment mustNotEqual null
      registerAttachment.attachment mustNotEqual null
      registerAttachment.attachment.id must equalTo("attach_00009238aOAIvVqfb9LrZh")
      registerAttachment.attachment.userId must equalTo("user_00009238aMBIIrS5Rdncq9")
      registerAttachment.attachment.externalId must equalTo("tx_00008zIcpb1TB4yeIFXMzx")
      registerAttachment.attachment.fileUrl must equalTo("https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png")
      registerAttachment.attachment.fileType must equalTo("image/png")
      registerAttachment.attachment.created must equalTo("2015-11-12T18:37:02Z")
    }

    "Deregister an attachment" in {
      val deregisterAttachment = Await.result(monzoClient.deregisterAttachment(""), 5.seconds)

      deregisterAttachment mustNotEqual null
    }
  }
}