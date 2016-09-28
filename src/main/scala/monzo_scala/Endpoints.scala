package monzo_scala

object Endpoints {
  val ApiEndpoint = "https://api.monzo.com"
  val exchangeAuthCode = ApiEndpoint + "/oauth2/token"
  val refreshToken = ApiEndpoint + "/oauth2/token"
  val whoami = ApiEndpoint + "/ping/whoami"
  val listAccounts = ApiEndpoint + "/accounts"
  val balance = ApiEndpoint + "/balance"
  def transaction(id: String) = ApiEndpoint + s"/transactions/$id"
  val listTransactions = ApiEndpoint + "/transactions"
  def annotateTransaction(id: String) = ApiEndpoint + s"/transactions/$id"
  val createFeedItem = ApiEndpoint + "/feed"
  val registerWebhook = ApiEndpoint + "/webhooks"
  val listWebhook = ApiEndpoint + "/webhooks"
  def deleteWebhook(id: String) = ApiEndpoint + s"/webhooks/$id"
  val uploadAttachment = ApiEndpoint + "/attachment/upload"
  val registerAttachment = ApiEndpoint + "/attachment/register"
  val deregisterAttachment = ApiEndpoint + "/attachment/deregister"
}
