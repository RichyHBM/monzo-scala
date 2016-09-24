package models

import monzo_scala.models._
import org.specs2.mutable.Specification
import play.api.libs.json.Json

class FeedItemsModelsSpec extends Specification {
  val feedItemsJson = Json.stringify(Json.parse("""{}""".stripMargin))

  "FeedItems Model" should {
    "Construct from json" in {
      val feedItems = FeedItems.fromJson(feedItemsJson)

      feedItems mustNotEqual null
    }

    "Convert to json" in {
      val feedItems = FeedItems()

      feedItems.toJson() must equalTo(feedItemsJson)
    }
  }
}