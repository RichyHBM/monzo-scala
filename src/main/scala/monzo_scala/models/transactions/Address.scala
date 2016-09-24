package monzo_scala.models.transactions

import monzo_scala.models.JsonModel
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class Address(address: String,
                   city: String,
                   country: String,
                   latitude: Double,
                   longitude: Double,
                   postCode: String,
                   region: String) extends JsonModel {

  override def toJson(): String = Json.stringify(Json.toJson(this))
}

object Address {
  def fromJson(json: String): Address = Json.parse(json).as[Address]

  implicit val reads: Reads[Address] = (
    (JsPath \ "address").read[String] and
      (JsPath \ "city").read[String] and
      (JsPath \ "country").read[String] and
      (JsPath \ "latitude").read[Double] and
      (JsPath \ "longitude").read[Double] and
      (JsPath \ "postcode").read[String] and
      (JsPath \ "region").read[String]
    ) (Address.apply _)

  implicit val writes: Writes[Address] = (
    (JsPath \ "address").write[String] and
      (JsPath \ "city").write[String] and
      (JsPath \ "country").write[String] and
      (JsPath \ "latitude").write[Double] and
      (JsPath \ "longitude").write[Double] and
      (JsPath \ "postcode").write[String] and
      (JsPath \ "region").write[String]
    ) (unlift(Address.unapply))
}