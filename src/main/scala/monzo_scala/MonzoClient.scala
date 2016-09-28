package monzo_scala

import monzo_scala.http.{HttpClient, ScalaJHttpClient}
import monzo_scala.models.{Balance, Feed}
import monzo_scala.models.accounts.AccountList
import monzo_scala.models.attachments.{DeregisterAttachment, RegisterAttachment, UploadAttachment}
import monzo_scala.models.authentication.{Access, WhoAmI}
import monzo_scala.models.transactions.{AnnotateTransaction, SingleTransaction, TransactionList}
import monzo_scala.models.webhooks.{DeleteWebhook, RegisterWebhook, WebhookList}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class MonzoClient(accessToken: String, httpClient: HttpClient) extends Traits.IMonzoAPI {
  val headers = Map("Authorization" -> s"Bearer $accessToken")

  def this(accessToken: String) {
    this(accessToken, ScalaJHttpClient())
  }

  override def whoAmI(): Future[WhoAmI] = {
    httpClient.makeRequest(Enums.Methods.Get,
      Endpoints.whoami,
      headers,
      Map()
    ).map(s => WhoAmI.fromJson(s))
  }

  override def listAccounts(): Future[AccountList] = {
    httpClient.makeRequest(Enums.Methods.Get,
      Endpoints.listAccounts,
      headers,
      Map()
    ).map(s => AccountList.fromJson(s))
  }

  override def getBalance(accountId: String): Future[Balance] = {
    httpClient.makeRequest(Enums.Methods.Get,
      Endpoints.balance + s"?account_id=$accountId",
      headers,
      Map()
    ).map(s => Balance.fromJson(s))
  }

  override def getTransaction(transactionId: String): Future[SingleTransaction] = {
    httpClient.makeRequest(Enums.Methods.Get,
      Endpoints.transaction(transactionId) + "?expand[]==merchant",
      headers,
      Map()
    ).map(s => SingleTransaction.fromJson(s))
  }

  override def listTransactions(accountId: String, limit: Option[Int], since: Option[String], before: Option[String]): Future[TransactionList] = {

    val limitQuery = limit match {
      case Some(i) => s"&limit=$i"
      case None => ""
    }

    val sinceQuery = since match {
      case Some(s) => s"&since=$s"
      case None => ""
    }

    val beforeQuery = before match {
      case Some(b) => s"&before=$b"
      case None => ""
    }

    val urlQuery = s"?account_id=$accountId" + limitQuery + sinceQuery + beforeQuery + "&expand[]==merchant"

    httpClient.makeRequest(Enums.Methods.Get,
      Endpoints.listTransactions + urlQuery,
      headers,
      Map()
    ).map(s => TransactionList.fromJson(s))
  }

  override def annotateTransaction(transactionId: String, metadata: Map[String, String]): Future[AnnotateTransaction] = {
    val metadataFormat = metadata.map(meta => s"metadata[${meta._1}]" -> meta._2)

    httpClient.makeRequest(Enums.Methods.Patch,
      Endpoints.annotateTransaction(transactionId)  + "?expand[]==merchant",
      headers,
      metadataFormat
    ).map(s => AnnotateTransaction.fromJson(s))
  }

  override def createBasicFeedItem(accountId: String, url: Option[String], title: String, imageUrl: String, body: Option[String], backgroundColor: Option[String], titleColor: Option[String], bodyColor: Option[String]): Future[Feed] = {

    val form = Map(
      "account_id" -> accountId,
      "type" -> "basic",
      "params[title]" -> title,
      "params[image_url]" -> imageUrl
    ) ++ (url match {
      case Some(u) => Map("url" -> u)
      case None => Map()
    }) ++ (body match {
      case Some(b) => Map("params[body]" -> b)
      case None => Map()
    }) ++ (backgroundColor match {
      case Some(bk) => Map("params[background_color]" -> bk)
      case None => Map()
    }) ++ (titleColor match {
      case Some(t) => Map("params[title_color]" -> t)
      case None => Map()
    }) ++ (bodyColor match {
      case Some(bc) => Map("params[body_color]" -> bc)
      case None => Map()
    })

    httpClient.makeRequest(Enums.Methods.Post,
      Endpoints.createFeedItem,
      headers,
      form
    ).map(s => Feed.fromJson(s))
  }

  override def registerWebhook(accountId: String, callback: String): Future[RegisterWebhook] = {
    httpClient.makeRequest(Enums.Methods.Post,
      Endpoints.registerWebhook,
      headers,
      Map(
        "account_id" -> accountId,
        "url" -> callback
      )
    ).map(s => RegisterWebhook.fromJson(s))
  }

  override def listWebhooks(accountId: String): Future[WebhookList] = {
    httpClient.makeRequest(Enums.Methods.Get,
      Endpoints.listWebhook + s"?account_id=$accountId",
      headers,
      Map()
    ).map(s => WebhookList.fromJson(s))
  }

  override def deleteWebhook(webhookId: String): Future[DeleteWebhook] = {
    httpClient.makeRequest(Enums.Methods.Delete,
      Endpoints.deleteWebhook(webhookId),
      headers,
      Map()
    ).map(s => DeleteWebhook.fromJson(s))
  }

  override def uploadAttachment(fileName: String, fileType: String): Future[UploadAttachment] = {
    httpClient.makeRequest(Enums.Methods.Post,
      Endpoints.uploadAttachment,
      headers,
      Map(
        "file_name" -> fileName,
        "file_type" -> fileType
      )
    ).map(s => UploadAttachment.fromJson(s))
  }

  override def registerAttachment(externalId: String, fileUrl: String, fileType: String): Future[RegisterAttachment] = {
    httpClient.makeRequest(Enums.Methods.Post,
      Endpoints.registerAttachment,
      headers,
      Map("external_id" -> externalId,
        "file_type" -> fileType,
        "file_url" -> fileUrl)
    ).map(s => RegisterAttachment.fromJson(s))
  }

  override def deregisterAttachment(id: String): Future[DeregisterAttachment] = {
    httpClient.makeRequest(Enums.Methods.Post,
      Endpoints.deregisterAttachment,
      headers,
      Map("id" -> id)
    ).map(s => DeregisterAttachment.fromJson(s))
  }
}

object MonzoClient {
  val httpClient: HttpClient = ScalaJHttpClient()

  def refreshAccessToken(clientId: String, clientSecret: String, refreshToken: String): Future[Access] = {
    httpClient.makeRequest(Enums.Methods.Post,
      Endpoints.refreshToken,
      Map[String, String](),
      Map(
        "grant_type" -> "refresh_token",
        "client_id" -> clientId,
        "client_secret" -> clientSecret,
        "refresh_token" -> refreshToken
      )
    ).map(s => Access.fromJson(s))
  }

  def exchangeAuthorizationCode(clientId: String, clientSecret: String, redirectURL: String, authCode: String): Future[Access] = {
    httpClient.makeRequest(Enums.Methods.Post,
      Endpoints.exchangeAuthCode,
      Map[String, String](),
      Map(
        "grant_type" -> "authorization_code",
        "client_id" -> clientId,
        "client_secret" -> clientSecret,
        "redirect_uri" -> redirectURL,
        "code" -> authCode
      )
    ).map(s => Access.fromJson(s))
  }
}