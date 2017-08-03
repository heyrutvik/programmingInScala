package ch30

class Point2(val x: Int, val y: Int) {

  override def hashCode = (x, y).##

  // run-time check for 3: transitive check
  override def equals(other: Any) = other match {
    case that: Point2 =>
      (that canEquals this) && x == that.x && y == that.y
    case _ => false
  }

  def canEquals(other: Any) = other.isInstanceOf[Point2]
}

object Color extends Enumeration {
  val Red, Orange, Green, Blue, Violet = Value
}