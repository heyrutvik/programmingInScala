package ch30

/*
 *  1: reflexive =>   (x)     x.equals(x)
 *  2: symmetric =>   (x,y)   x.equals(y) <=> y.equals(x)
 *  3: transitive =>  (x,y,z) x.equals(y) && y.equals(z) <=> x.equals(z)
 *  4: consistency => (x, y)  x.equals(y) either true or false, 
 *                            return consistently same independently on outer environment
 *  5: for null =>    (x)     x.equals(null) = false
 */

/*
 *  2: symmetric
 *  two ways to make it work,
 *    -> make equals more general
 *      - add another case for parent type but then 3: transitive won't work.
 *      - check ColoredPoint2
 *    -> make equals more strict
 *      - treat object of different classes as different
 *      - check ColoredPoint3
 */
class ColoredPoint2(x: Int, y: Int, val color: Color.Value) extends Point1(x, y) {

  override def equals(other: Any) = other match {
    case that: ColoredPoint2 => color == that.color && super.equals(that)
    case that: Point1        => that.equals(this)
    case _                   => false
  }
}

object ColoredPoint2 {

  def apply() = {
    val p = new Point1(1, 2)
    val cp = new ColoredPoint2(1, 2, Color.Violet)
    val redp = new ColoredPoint2(1, 2, Color.Red)
    val bluep = new ColoredPoint2(1, 2, Color.Blue)

    println("2: symmetric")
    println("p == cp <=> " + (p == cp))
    println("cp == p <=> " + (cp == p))
    println

    println("3: transitive")
    println("redp == p <=> " + (redp == p))
    println("p == bluep <=> " + (p == bluep))
    println("redp == bluep <=> " + (redp == bluep))
    println("--------------------------------------------------------------------------------")

  }
}