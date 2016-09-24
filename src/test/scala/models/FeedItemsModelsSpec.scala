package models

import monzo_scala.models._
import org.specs2.mutable.Specification

class FeedItemsModelsSpec extends Specification {
  "FeedItems Model" should {
    "Construct from json" in {
      val feedItems = FeedItems.fromJson(ExampleJson.feedItemsJson)

      feedItems mustNotEqual null
    }

    "Convert to json" in {
      val feedItems = FeedItems()

      feedItems.toJson() must equalTo(ExampleJson.feedItemsJson)
    }
  }
}