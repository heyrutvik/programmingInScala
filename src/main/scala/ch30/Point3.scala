package ch30

class Point3(val x: Int, val y: Int) {

  override def hashCode = (x, y).##

  override def equals(other: Any) = other match {
    case that: Point3 =>
      (that canEquals this) && x == that.x && y == that.y
    case _ => false
  }

  def canEquals(other: Any) = other.isInstanceOf[Point3]
}