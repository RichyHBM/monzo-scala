package monzo_scala.models

import play.api.libs.json._

case class Feed() extends JsonModel {
  override def toJson(): String = "{}"
}

object Feed {
  def fromJson(json: String): Feed = Json.parse(json).as[Feed]

  implicit val reads: Reads[Feed] = Reads {
    _.validate[JsObject] map (_ => Feed())
  }
}