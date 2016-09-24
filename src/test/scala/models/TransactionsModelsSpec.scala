package models

import monzo_scala.models._
import org.specs2.mutable.Specification
import play.api.libs.json.Json

class TransactionsModelsSpec extends Specification {
  val singleTransactionJson = Json.stringify(Json.parse(
    """{
      |    "transaction": {
      |        "account_balance": 13013,
      |        "amount": -510,
      |        "created": "2015-08-22T12:20:18Z",
      |        "currency": "GBP",
      |        "description": "THE DE BEAUVOIR DELI C LONDON        GBR",
      |        "id": "tx_00008zIcpb1TB4yeIFXMzx",
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
      |        },
      |        "metadata": {},
      |        "notes": "Salmon sandwich 🍞",
      |        "is_load": false,
      |        "settled": "2015-08-23T12:20:18Z",
      |        "category": "eating_out"
      |    }
      |}""".stripMargin))

  val listTransactionsJson = Json.stringify(Json.parse(
    """{
      |    "transactions": [
      |        {
      |            "account_balance": 13013,
      |            "amount": -510,
      |            "created": "2015-08-22T12:20:18Z",
      |            "currency": "GBP",
      |            "description": "THE DE BEAUVOIR DELI C LONDON        GBR",
      |            "id": "tx_00008zIcpb1TB4yeIFXMzx",
      |            "merchant": {
      |                "address": {
      |                    "address": "98 Southgate Road",
      |                    "city": "London",
      |                    "country": "GB",
      |                    "latitude": 51.54151,
      |                    "longitude": -0.08482400000002599,
      |                    "postcode": "N1 3JD",
      |                    "region": "Greater London"
      |                },
      |                "created": "2015-08-22T12:20:18Z",
      |                "group_id": "grp_00008zIcpbBOaAr7TTP3sv",
      |                "id": "merch_00008zIcpbAKe8shBxXUtl",
      |                "logo": "https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg",
      |                "emoji": "🍞",
      |                "name": "The De Beauvoir Deli Co.",
      |                "category": "eating_out"
      |            },
      |            "metadata": {},
      |            "notes": "Salmon sandwich 🍞",
      |            "is_load": false,
      |            "settled": "2015-08-23T12:20:18Z",
      |            "category": "eating_out"
      |        },
      |        {
      |            "account_balance": 12334,
      |            "amount": -679,
      |            "created": "2015-08-23T16:15:03Z",
      |            "currency": "GBP",
      |            "description": "VUE BSL LTD            ISLINGTON     GBR",
      |            "id": "tx_00008zL2INM3xZ41THuRF3",
      |            "merchant": {
      |                "address": {
      |                    "address": "98 Southgate Road",
      |                    "city": "London",
      |                    "country": "GB",
      |                    "latitude": 51.54151,
      |                    "longitude": -0.08482400000002599,
      |                    "postcode": "N1 3JD",
      |                    "region": "Greater London"
      |                },
      |                "created": "2015-08-22T12:20:18Z",
      |                "group_id": "grp_00008zIcpbBOaAr7TTP3sv",
      |                "id": "merch_00008zIcpbAKe8shBxXUtl",
      |                "logo": "https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg",
      |                "emoji": "🍞",
      |                "name": "The De Beauvoir Deli Co.",
      |                "category": "eating_out"
      |            },
      |            "metadata": {},
      |            "notes": "",
      |            "is_load": false,
      |            "settled": "2015-08-24T16:15:03Z",
      |            "category": "eating_out"
      |        }
      |    ]
      |}""".stripMargin))

  val annotateTransactionJson = Json.stringify(Json.parse(
    """{
      |    "transaction": {
      |        "account_balance": 12334,
      |        "amount": -679,
      |        "created": "2015-08-23T16:15:03Z",
      |        "currency": "GBP",
      |        "description": "VUE BSL LTD            ISLINGTON     GBR",
      |        "id": "tx_00008zL2INM3xZ41THuRF3",
      |        "merchant": {
      |                "address": {
      |                    "address": "98 Southgate Road",
      |                    "city": "London",
      |                    "country": "GB",
      |                    "latitude": 51.54151,
      |                    "longitude": -0.08482400000002599,
      |                    "postcode": "N1 3JD",
      |                    "region": "Greater London"
      |                },
      |                "created": "2015-08-22T12:20:18Z",
      |                "group_id": "grp_00008zIcpbBOaAr7TTP3sv",
      |                "id": "merch_00008zIcpbAKe8shBxXUtl",
      |                "logo": "https://pbs.twimg.com/profile_images/527043602623389696/68_SgUWJ.jpeg",
      |                "emoji": "🍞",
      |                "name": "The De Beauvoir Deli Co.",
      |                "category": "eating_out"
      |            },
      |        "metadata": {
      |            "foo": "bar"
      |        },
      |        "notes": "",
      |        "is_load": false,
      |        "settled": "2015-08-24T16:15:03Z",
      |        "category": "eating_out"
      |    }
      |}""".stripMargin))

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
      val singleTransaction = SingleTransaction.fromJson(singleTransactionJson)

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

      singleTransaction.toJson() must equalTo(singleTransactionJson)
    }
  }

  "ListTransaction Model" should {
    "Construct from json" in {
      val listTransactions = ListTransactions.fromJson(listTransactionsJson)

      listTransactions mustNotEqual null
      listTransactions.transactions.length must equalTo(2)

      listTransactions.transactions.head.accountBalance must equalTo(13013)
      listTransactions.transactions.head.amount must equalTo(-510)
      listTransactions.transactions.head.created must equalTo("2015-08-22T12:20:18Z")
      listTransactions.transactions.head.currency must equalTo("GBP")
      listTransactions.transactions.head.description must equalTo("THE DE BEAUVOIR DELI C LONDON        GBR")
      listTransactions.transactions.head.id must equalTo("tx_00008zIcpb1TB4yeIFXMzx")
      listTransactions.transactions.head.merchant must equalTo(merchant)
      listTransactions.transactions.head.metadata must equalTo(Map[String, String]())
      listTransactions.transactions.head.notes must equalTo("Salmon sandwich 🍞")
      listTransactions.transactions.head.isLoad must equalTo(false)
      listTransactions.transactions.head.settled must equalTo("2015-08-23T12:20:18Z")
      listTransactions.transactions.head.category must equalTo("eating_out")

      listTransactions.transactions(1).accountBalance must equalTo(12334)
      listTransactions.transactions(1).amount must equalTo(-679)
      listTransactions.transactions(1).created must equalTo("2015-08-23T16:15:03Z")
      listTransactions.transactions(1).currency must equalTo("GBP")
      listTransactions.transactions(1).description must equalTo("VUE BSL LTD            ISLINGTON     GBR")
      listTransactions.transactions(1).id must equalTo("tx_00008zL2INM3xZ41THuRF3")
      listTransactions.transactions(1).merchant must equalTo(merchant)
      listTransactions.transactions(1).metadata must equalTo(Map[String, String]())
      listTransactions.transactions(1).notes must equalTo("")
      listTransactions.transactions(1).isLoad must equalTo(false)
      listTransactions.transactions(1).settled must equalTo("2015-08-24T16:15:03Z")
      listTransactions.transactions(1).category must equalTo("eating_out")
    }

    "Convert to json" in {
      val listTransactions = ListTransactions(
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

      listTransactions.toJson() must equalTo(listTransactionsJson)
    }
  }

  "AnnotateTransaction Model" should {
    "Construct from json" in {
      val annotateTransaction = AnnotateTransactions.fromJson(annotateTransactionJson)

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
      val annotateTransaction = AnnotateTransactions(ExpandedTransaction(
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

      annotateTransaction.toJson() must equalTo(annotateTransactionJson)
    }
  }
}