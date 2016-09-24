package monzo_scala.models

import play.api.libs.json._

case class FeedItems() extends JsonModel {
  override def toJson(): String = "{}"
}

object FeedItems {
  def fromJson(json: String): FeedItems = Json.parse(json).as[FeedItems]

  implicit val reads: Reads[FeedItems] = Reads {
    _.validate[JsObject] map (_ => FeedItems())
  }
}