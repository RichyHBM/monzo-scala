package models

import monzo_scala.models._
import org.specs2.mutable.Specification

class AuthenticationModelsSpec extends Specification {
  "Access Model" should {
    "Construct from json" in {
      val access = Access.fromJson(ExampleJson.accessJson)

      access mustNotEqual null
      access.accessToken must equalTo("access_token")
      access.clientId must equalTo("client_id")
      access.expiresIn must equalTo(21600)
      access.refreshToken must equalTo("refresh_token")
      access.tokenType must equalTo("Bearer")
      access.userId must equalTo("user_id")
    }

    "Convert to json" in {
      val access = Access("access_token", "client_id", 21600, "refresh_token", "Bearer", "user_id")

      access.toJson() must equalTo(ExampleJson.accessJson)
    }
  }

  "WhoAmI Model" should {
    "Construct from json" in {
      val whoAmI = WhoAmI.fromJson(ExampleJson.whoAmIJson)

      whoAmI mustNotEqual null
      whoAmI.authenticated must equalTo(true)
      whoAmI.clientId must equalTo("client_id")
      whoAmI.userId must equalTo("user_id")
    }

    "Convert to json" in {
      val whoAmI = WhoAmI(true, "client_id", "user_id")
      whoAmI.toJson() must equalTo(ExampleJson.whoAmIJson)
    }
  }
}