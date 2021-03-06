package ch30

class Point2(val x: Int, val y: Int) {

  override def hashCode = (x, y).##

  override def equals(other: Any) = other match {
    case that: Point2 =>
      x == that.x && y == that.y && getClass == that.getClass
    case _ => false
  }

  def canEquals(other: Any) = other.isInstanceOf[Point3]
}