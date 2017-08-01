package ch20

object Enum {

  object Color extends Enumeration {
    val Red, Green, Blue = Value
  }

  object Direction extends Enumeration {
    val North = Value("North")
    val East = Value("East")
    val South = Value("South")
    val West = Value("West")
  }

  val red = Color.Red
  val west = Direction.West
  val westId = west.id
  val whichDir = Direction(westId)
}