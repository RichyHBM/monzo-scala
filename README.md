Monzo Scala API Client
======================

Simple scala client for the [Monzo](https://monzo.com/) API

Monzo client expects an access token which it will use for all requests to Monzo. It uses an HTTPClient in order to talk to Monzo, the basic implementation makes use of scalaj-http but you may want to supply your own implementation of the HTTPClient trait.

```scala
val monzoClient = MonzoClient("access_token")

//Or optionally
val myHttpClientImpl: HttpClient = MyHttpClient()
val monzoClient = MonzoClient("access_token", myHttpClientImpl)
```

The MonzoClient offers the following methods to make API requests, all of which return a Future of a response.
```scala
def whoAmI(): Future[WhoAmI]

def listAccounts(): Future[AccountList]

def getBalance(accountId: String): Future[Balance]

def getTransaction(transactionId: String, expandMerchants: Boolean): Future[SingleTransaction]

def listTransactions(accountId: String,
                     expandMerchants: Boolean,
                     limit: Option[Int],
                     since: Option[String],
                     before: Option[String]): Future[TransactionList]

def annotateTransaction(transactionId: String,
                        expandMerchants: Boolean,
                        metadata: Map[String, String]): Future[AnnotateTransaction]

def createBasicFeedItem(accountId: String,
                        url: Option[String],
                        title: String,
                        imageUrl: String,
                        body: Option[String],
                        backgroundColor: Option[String],
                        titleColor: Option[String],
                        bodyColor: Option[String]): Future[Feed]

def registerWebhook(accountId: String, callback: String): Future[RegisterWebhook]

def listWebhooks(accountId: String): Future[WebhookList]

def deleteWebhook(webhookId: String): Future[DeleteWebhook]  

def uploadAttachment(fileName: String, fileType: String): Future[UploadAttachment]

def registerAttachment(externalId: String, fileUrl: String, fileType: String): Future[RegisterAttachment]

def deregisterAttachment(id: String): Future[DeregisterAttachment]
```

The MonzoClient object also offers the following helper methods to aid in the exchange and refresh of tokens.
```scala
def exchangeAuthorizationCode(clientId: String, clientSecret: String, redirectURL: String, authCode: String): Future[Access]

def refreshAccessToken(clientId: String, clientSecret: String, refreshToken: String): Future[Access]
```

The only difference between this client and the response from the Monzo API is the Merchant field on Transactions.

From the API docs, the Merchant field may be either an ID or a Merchant object (depending on whether you have requested an expansion of the merchant field in the request) Scala however does not allow for a field to be of 2 different types, so for the moment the Merchant member on Trasactions is simply an Option of a Merchant.

Released under the simplified BSD license:

> Copyright (c) 2016, RichyHBM
> All rights reserved.
>
> Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
>
> 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
>
> 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
>
> 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
>
> THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
