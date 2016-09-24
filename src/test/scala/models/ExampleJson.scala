package models

import play.api.libs.json.Json

object ExampleJson {
  val accountsJson = Json.stringify(Json.parse(
    """{
      |    "accounts": [
      |        {
      |            "id": "acc_00009237aqC8c5umZmrRdh",
      |            "description": "Peter Pan's Account",
      |            "created": "2015-11-13T12:17:42Z"
      |        }
      |    ]
      |}""".stripMargin))

  val uploadAttachmentJson = Json.stringify(Json.parse(
    """{
      |    "file_url":"https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png",
      |    "upload_url":"https://mondo-image-uploads.s3.amazonaws.com/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png?AWSAccessKeyId=AKIAIR3IFH6UCTCXB5PQ\u0026Expires=1447353431\u0026Signature=k2QeDCCQQHaZeynzYKckejqXRGU%!D(MISSING)"
      |}""".stripMargin))

  val registerAttachmentJson = Json.stringify(Json.parse(
    """{
      |    "attachment": {
      |        "id": "attach_00009238aOAIvVqfb9LrZh",
      |        "user_id": "user_00009238aMBIIrS5Rdncq9",
      |        "external_id": "tx_00008zIcpb1TB4yeIFXMzx",
      |        "file_url": "https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png",
      |        "file_type": "image/png",
      |        "created": "2015-11-12T18:37:02Z"
      |    }
      |}""".stripMargin))

  val deregisterAttachmentJson = Json.stringify(Json.parse("""{}""".stripMargin))

  val accessJson = Json.stringify(Json.parse(
    """{
      |    "access_token": "access_token",
      |    "client_id": "client_id",
      |    "expires_in": 21600,
      |    "refresh_token": "refresh_token",
      |    "token_type": "Bearer",
      |    "user_id": "user_id"
      |}""".stripMargin))

  val whoAmIJson = Json.stringify(Json.parse(
    """{
      |    "authenticated": true,
      |    "client_id": "client_id",
      |    "user_id": "user_id"
      |}""".stripMargin))

  val balanceJson = Json.stringify(Json.parse(
    """{
      |    "balance": 5000,
      |    "currency": "GBP",
      |    "spend_today": 0
      |}""".stripMargin))

  val feedJson = Json.stringify(Json.parse("""{}""".stripMargin))

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

  val transactionListJson = Json.stringify(Json.parse(
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

  val registerWebhookJson = Json.stringify(Json.parse(
    """{
      |    "webhook": {
      |        "account_id": "account_id",
      |        "id": "webhook_id",
      |        "url": "http://example.com"
      |    }
      |}""".stripMargin))

  val webhookListJson = Json.stringify(Json.parse(
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
}
