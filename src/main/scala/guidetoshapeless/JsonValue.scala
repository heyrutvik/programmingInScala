package guidetoshapeless

sealed trait JsonValue

case class JsonObject(fields: List[(String, JsonValue)]) extends JsonValue

case class JsonArray(items: List[JsonValue]) extends JsonValue

case class JsonString(value: String) extends JsonValue

case class JsonNumber(value: Double) extends JsonValue

case class JsonBoolean(value: Boolean) extends JsonValue

case object JsonNull extends JsonValue

trait JsonEncoder[A] {
  def encode(value: A): JsonValue
}

object JsonEncoder {
  def apply[A](implicit enc: JsonEncoder[A]): JsonEncoder[A] = enc

  def instance[A](func: A => JsonValue): JsonEncoder[A] = { value =>
    func(value)
  }

  implicit val stringEncoder: JsonEncoder[String] = value => JsonString(value)
  implicit val doubleEncoder: JsonEncoder[Double] = value => JsonNumber(value)
  implicit val intEncoder: JsonEncoder[Int] = value => JsonNumber(value)
  implicit val booleanEncoder: JsonEncoder[Boolean] = value => JsonBoolean(value)
  implicit def listEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[List[A]] = { value =>
    JsonArray(value.map(enc.encode(_)))
  }
  implicit def optionEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[Option[A]] = { value =>
    value.map(enc.encode(_)).getOrElse(JsonNull)
  }
}