import models.ExampleJson
import monzo_scala.Endpoints
import monzo_scala.Enums.Methods
import monzo_scala.http.HttpClient

import scala.concurrent.Future
import scala.util.matching.Regex
import scala.concurrent.ExecutionContext.Implicits.global

case class MockMonzoHttpClient() extends HttpClient {
  override def makeRequest(method: monzo_scala.Enums.Methods.Value,
                           url: String,
                           headers: Map[String, String],
                           form: Map[String, String]): Future[String] = {

    Future {

      val balanceUrlRegex: Regex = (Endpoints.balance + "?account_id=.+").r
      val transactionUrlRegex: Regex = Endpoints.transaction(".+").r
      val transactionsUrlRegex: Regex = (Endpoints.listTransactions + "?account_id=.+").r
      val annotateTransactionUrlRegex: Regex = Endpoints.annotateTransaction(".+").r
      val listWebhooksUrlRegex: Regex =  (Endpoints.registerWebhook + "?account_id=.+").r
      val deleteWebhookUrlRegex: Regex = Endpoints.deleteWebhook(".+").r

      (method, url) match {
        case (Methods.Get, Endpoints.whoami) => ExampleJson.whoAmIJson
        case (Methods.Post, Endpoints.refreshToken) => ExampleJson.accessJson
        case (Methods.Get, Endpoints.listAccounts) => ExampleJson.accountsJson
        case (Methods.Get, balanceUrlRegex()) => ExampleJson.balanceJson
        case (Methods.Get, transactionUrlRegex()) => ExampleJson.singleTransactionJson
        case (Methods.Get, transactionsUrlRegex()) => ExampleJson.transactionListJson
        case (Methods.Patch, annotateTransactionUrlRegex()) => ExampleJson.annotateTransactionJson
        case (Methods.Post, Endpoints.createFeedItem) => ExampleJson.feedJson
        case (Methods.Post, Endpoints.registerWebhook) => ExampleJson.registerWebhookJson
        case (Methods.Get, listWebhooksUrlRegex()) => ExampleJson.webhookListJson
        case (Methods.Delete, deleteWebhookUrlRegex()) => ExampleJson.deleteWebhookJson
        case (Methods.Post, Endpoints.uploadAttachment) => ExampleJson.uploadAttachmentJson
        case (Methods.Post, Endpoints.registerAttachment) => ExampleJson.registerAttachmentJson
        case (Methods.Post, Endpoints.deregisterAttachment) => ExampleJson.deregisterAttachmentJson
      }
    }
  }
}
