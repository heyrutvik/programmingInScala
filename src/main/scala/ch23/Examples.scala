package ch23

object Examples {

  // later generators vary more rapidly than earlier one
  val res0 = for (x <- List(1, 2, 3); y <- List("one", "two", "three")) yield (x, y)
}