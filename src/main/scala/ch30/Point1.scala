package ch30

class Point1(val x: Int, val y: Int) {

  override def hashCode = (x, y).##

  override def equals(other: Any) = other match {
    case that: Point1 =>
      x == that.x && y == that.y
    case _ => false
  }
}

object Color extends Enumeration {
  val Red, Orange, Green, Blue, Violet = Value
}