
import models.ExampleJson
import monzo_scala.Endpoints
import monzo_scala.Enums.Methods
import monzo_scala.http.HttpClient

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class MockMonzoHttpClient() extends HttpClient {
  override def makeRequest(method: monzo_scala.Enums.Methods.Value,
                           url: String,
                           headers: Map[String, String],
                           form: Map[String, String]): Future[String] = {

    Future {
      val balanceUrl = Endpoints.balance + "?account_id="
      val transactionUrl = Endpoints.transaction("") + "?expand[]==merchant"
      val transactionsUrl = Endpoints.listTransactions + "?account_id="
      val annotateTransactionUrl = Endpoints.annotateTransaction("")
      val listWebhooksUrl =  Endpoints.registerWebhook + "?account_id="
      val deleteWebhookUrl = Endpoints.deleteWebhook("")

      (method, url) match {
        case (Methods.Get, Endpoints.whoami) => ExampleJson.whoAmIJson
        case (Methods.Post, Endpoints.refreshToken) => ExampleJson.accessJson
        case (Methods.Get, Endpoints.listAccounts) => ExampleJson.accountsJson
        case (Methods.Get, s) if balanceUrl.equalsIgnoreCase(s) => ExampleJson.balanceJson
        case (Methods.Get, s) if s.contains(transactionUrl) => ExampleJson.singleTransactionJson
        case (Methods.Get, s) if s.contains(transactionsUrl) => ExampleJson.transactionListJson
        case (Methods.Patch, s) if s.contains(annotateTransactionUrl) => ExampleJson.annotateTransactionJson
        case (Methods.Post, Endpoints.createFeedItem) => ExampleJson.feedJson
        case (Methods.Post, Endpoints.registerWebhook) => ExampleJson.registerWebhookJson
        case (Methods.Get, s) if listWebhooksUrl.equalsIgnoreCase(s) => ExampleJson.webhookListJson
        case (Methods.Delete, s) if deleteWebhookUrl.equalsIgnoreCase(s) => ExampleJson.deleteWebhookJson
        case (Methods.Post, Endpoints.uploadAttachment) => ExampleJson.uploadAttachmentJson
        case (Methods.Post, Endpoints.registerAttachment) => ExampleJson.registerAttachmentJson
        case (Methods.Post, Endpoints.deregisterAttachment) => ExampleJson.deregisterAttachmentJson
      }
    }
  }
}
