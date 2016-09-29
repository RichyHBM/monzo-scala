package monzo_scala

import monzo_scala.models.{Balance, Feed}
import monzo_scala.models.accounts.AccountList
import monzo_scala.models.attachments.{DeregisterAttachment, RegisterAttachment, UploadAttachment}
import monzo_scala.models.authentication.WhoAmI
import monzo_scala.models.transactions.{AnnotateTransaction, SingleTransaction, TransactionList}
import monzo_scala.models.webhooks.{DeleteWebhook, RegisterWebhook, WebhookList}

import scala.concurrent.Future

object Traits {

  trait IMonzoAPI extends IAuthentication with IAccounts with IBalance with ITransactions with IFeedItems with IWebhooks with IAttachments {}

  trait IAuthentication {
    def whoAmI(): Future[WhoAmI]
  }

  trait IAccounts {
    def listAccounts(): Future[AccountList]
  }

  trait IBalance {
    def getBalance(accountId: String): Future[Balance]
  }

  trait ITransactions {
    def getTransaction(transactionId: String, expandMerchants: Boolean): Future[SingleTransaction]

    def listTransactions(accountId: String, expandMerchants: Boolean, limit: Option[Int], since: Option[String], before: Option[String]): Future[TransactionList]

    def annotateTransaction(transactionId: String, expandMerchants: Boolean, metadata: Map[String, String]): Future[AnnotateTransaction]
  }

  trait IFeedItems {
    def createBasicFeedItem(accountId: String,
                            url: Option[String],
                            title: String,
                            imageUrl: String,
                            body: Option[String],
                            backgroundColor: Option[String],
                            titleColor: Option[String],
                            bodyColor: Option[String]): Future[Feed]
  }

  trait IWebhooks {
    def registerWebhook(accountId: String, callback: String): Future[RegisterWebhook]

    def listWebhooks(accountId: String): Future[WebhookList]

    def deleteWebhook(webhookId: String): Future[DeleteWebhook]
  }

  trait IAttachments {
    def uploadAttachment(fileName: String, fileType: String): Future[UploadAttachment]

    def registerAttachment(externalId: String, fileUrl: String, fileType: String): Future[RegisterAttachment]

    def deregisterAttachment(id: String): Future[DeregisterAttachment]
  }

}
