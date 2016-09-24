package models

import monzo_scala.models._
import org.specs2.mutable.Specification

class FeedModelsSpec extends Specification {
  "Feed Model" should {
    "Construct from json" in {
      val feed = Feed.fromJson(ExampleJson.feedJson)

      feed mustNotEqual null
    }

    "Convert to json" in {
      val feed = Feed()

      feed.toJson() must equalTo(ExampleJson.feedJson)
    }
  }
}