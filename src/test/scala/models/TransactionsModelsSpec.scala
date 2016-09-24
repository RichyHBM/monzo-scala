package models

import monzo_scala.models.transactions._
import org.specs2.mutable.Specification

class TransactionsModelsSpec extends Specification {
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

  "SingleTransaction Model" should {
    "Construct from json" in {
      val singleTransaction = SingleTransaction.fromJson(ExampleJson.singleTransactionJson)

      singleTransaction mustNotEqual null
      singleTransaction.transaction mustNotEqual null

      singleTransaction.transaction.accountBalance must equalTo(13013)
      singleTransaction.transaction.amount must equalTo(-510)
      singleTransaction.transaction.created must equalTo("2015-08-22T12:20:18Z")
      singleTransaction.transaction.currency must equalTo("GBP")
      singleTransaction.transaction.description must equalTo("THE DE BEAUVOIR DELI C LONDON        GBR")
      singleTransaction.transaction.id must equalTo("tx_00008zIcpb1TB4yeIFXMzx")

      singleTransaction.transaction.merchant mustNotEqual null
      singleTransaction.transaction.merchant.address mustNotEqual null
      singleTransaction.transaction.merchant.address.address must equalTo("98 Southgate Road")
      singleTransaction.transaction.merchant.address.city must equalTo("London")
      singleTransaction.transaction.merchant.address.country must equalTo("GB")
      singleTransaction.transaction.merchant.address.latitude must equalTo(51.54151)
      singleTransaction.transaction.merchant.address.longitude must equalTo(-0.08482400000002599)
      singleTransaction.transaction.merchant.address.postCode must equalTo("N1 3JD")
      singleTransaction.transaction.merchant.address.region must equalTo("Greater London")

      singleTransaction.transaction.merchant.created must equalTo("2015-08-22T12:20:18Z")
      singleTransaction.transaction.merchant.groupId must equalTo("grp_00008zIcpbBOaAr7TTP3sv")
      singleTransaction.transaction.merchant.id must equalTo("merch_00008zIcpbAKe8shBxXUtl")
      singleTransaction.transaction.merchant.logo must equalTo("https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg")
      singleTransaction.transaction.merchant.emoji must equalTo("🍞")
      singleTransaction.transaction.merchant.name must equalTo("The De Beauvoir Deli Co.")
      singleTransaction.transaction.merchant.category must equalTo("eating_out")

      singleTransaction.transaction.metadata must equalTo(Map[String, String]())
      singleTransaction.transaction.notes must equalTo("Salmon sandwich 🍞")
      singleTransaction.transaction.isLoad must equalTo(false)
      singleTransaction.transaction.settled must equalTo("2015-08-23T12:20:18Z")
      singleTransaction.transaction.category must equalTo("eating_out")
    }

    "Convert to json" in {
      val transaction = ExpandedTransaction(
        13013,
        -510,
        "2015-08-22T12:20:18Z",
        "GBP",
        "THE DE BEAUVOIR DELI C LONDON        GBR",
        "tx_00008zIcpb1TB4yeIFXMzx",
        merchant,
        Map[String, String](),
        "Salmon sandwich 🍞",
        false,
        "2015-08-23T12:20:18Z",
        "eating_out")

      val singleTransaction = SingleTransaction(transaction)

      singleTransaction.toJson() must equalTo(ExampleJson.singleTransactionJson)
    }
  }

  "ListTransaction Model" should {
    "Construct from json" in {
      val transactionList = TransactionList.fromJson(ExampleJson.transactionListJson)

      transactionList mustNotEqual null
      transactionList.transactions.length must equalTo(2)

      transactionList.transactions.head.accountBalance must equalTo(13013)
      transactionList.transactions.head.amount must equalTo(-510)
      transactionList.transactions.head.created must equalTo("2015-08-22T12:20:18Z")
      transactionList.transactions.head.currency must equalTo("GBP")
      transactionList.transactions.head.description must equalTo("THE DE BEAUVOIR DELI C LONDON        GBR")
      transactionList.transactions.head.id must equalTo("tx_00008zIcpb1TB4yeIFXMzx")
      transactionList.transactions.head.merchant must equalTo(merchant)
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
      transactionList.transactions(1).merchant must equalTo(merchant)
      transactionList.transactions(1).metadata must equalTo(Map[String, String]())
      transactionList.transactions(1).notes must equalTo("")
      transactionList.transactions(1).isLoad must equalTo(false)
      transactionList.transactions(1).settled must equalTo("2015-08-24T16:15:03Z")
      transactionList.transactions(1).category must equalTo("eating_out")
    }

    "Convert to json" in {
      val transactionList = TransactionList(
        Seq(
          ExpandedTransaction(
            13013,
            -510,
            "2015-08-22T12:20:18Z",
            "GBP",
            "THE DE BEAUVOIR DELI C LONDON        GBR",
            "tx_00008zIcpb1TB4yeIFXMzx",
            merchant,
            Map[String, String](),
            "Salmon sandwich 🍞",
            false,
            "2015-08-23T12:20:18Z",
            "eating_out"),
          ExpandedTransaction(
            12334,
            -679,
            "2015-08-23T16:15:03Z",
            "GBP",
            "VUE BSL LTD            ISLINGTON     GBR",
            "tx_00008zL2INM3xZ41THuRF3",
            merchant,
            Map[String, String](),
            "",
            false,
            "2015-08-24T16:15:03Z",
            "eating_out")))

      transactionList.toJson() must equalTo(ExampleJson.transactionListJson)
    }
  }

  "AnnotateTransaction Model" should {
    "Construct from json" in {
      val annotateTransaction = AnnotateTransaction.fromJson(ExampleJson.annotateTransactionJson)

      annotateTransaction mustNotEqual null
      annotateTransaction.transaction mustNotEqual null

      annotateTransaction.transaction.accountBalance must equalTo(12334)
      annotateTransaction.transaction.amount must equalTo(-679)
      annotateTransaction.transaction.created must equalTo("2015-08-23T16:15:03Z")
      annotateTransaction.transaction.currency must equalTo("GBP")
      annotateTransaction.transaction.description must equalTo("VUE BSL LTD            ISLINGTON     GBR")
      annotateTransaction.transaction.id must equalTo("tx_00008zL2INM3xZ41THuRF3")
      annotateTransaction.transaction.merchant must equalTo(merchant)
      annotateTransaction.transaction.metadata must equalTo(Map[String, String]("foo" -> "bar"))
      annotateTransaction.transaction.notes must equalTo("")
      annotateTransaction.transaction.isLoad must equalTo(false)
      annotateTransaction.transaction.settled must equalTo("2015-08-24T16:15:03Z")
      annotateTransaction.transaction.category must equalTo("eating_out")
    }

    "Convert to json" in {
      val annotateTransaction = AnnotateTransaction(ExpandedTransaction(
        12334,
        -679,
        "2015-08-23T16:15:03Z",
        "GBP",
        "VUE BSL LTD            ISLINGTON     GBR",
        "tx_00008zL2INM3xZ41THuRF3",
        merchant,
        Map[String, String]("foo" -> "bar"),
        "",
        false,
        "2015-08-24T16:15:03Z",
        "eating_out"
      ))

      annotateTransaction.toJson() must equalTo(ExampleJson.annotateTransactionJson)
    }
  }
}