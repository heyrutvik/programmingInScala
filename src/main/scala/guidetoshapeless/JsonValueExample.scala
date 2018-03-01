package guidetoshapeless

import shapeless.LabelledGeneric
import JsonObjectEncoder._

object JsonValueExample extends App {

  val iceCream = IceCream("A1", 21, false)
  val gen = LabelledGeneric[IceCream].to(iceCream)
  println(JsonEncoder[IceCream].encode(iceCream))
}
