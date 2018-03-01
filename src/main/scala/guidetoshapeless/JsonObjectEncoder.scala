package guidetoshapeless

import shapeless.labelled.FieldType
import shapeless.{::, HList, HNil, Lazy, Witness, LabelledGeneric}

trait JsonObjectEncoder[A] extends JsonEncoder[A] {
  def encode(value: A): JsonObject
}

object JsonObjectEncoder {
  def instance[A](fn: A => JsonObject): JsonObjectEncoder[A] = { value =>
    fn(value)
  }

  implicit val hnilEncoder: JsonObjectEncoder[HNil] = value => JsonObject(Nil)

  implicit def hlistEncoder[K <: Symbol, H, T <: HList](
     implicit
     witness: Witness.Aux[K],
     hEncoder: Lazy[JsonEncoder[H]],
     tEncoder: JsonObjectEncoder[T]
   ): JsonObjectEncoder[FieldType[K, H] :: T] = {
    val fieldName: String = witness.value.name
    instance { hlist =>
      val head = hEncoder.value.encode(hlist.head)
      val tail = tEncoder.encode(hlist.tail)
      JsonObject((fieldName, head) :: tail.fields)
    }
  }

  implicit def genericObjectEncoder[A, H <: HList](
    implicit
    generic: LabelledGeneric.Aux[A, H],
    hEncoder: Lazy[JsonObjectEncoder[H]]
  ): JsonEncoder[A] = {
    instance { value =>
      hEncoder.value.encode(generic.to(value))
    }
  }
}
