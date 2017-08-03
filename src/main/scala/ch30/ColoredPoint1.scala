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
 *    -> make equals more strick
 */
class ColoredPoint1(x: Int, y: Int, val color: Color.Value) extends Point1(x, y) {

  override def equals(other: Any) = other match {
    case that: ColoredPoint1 => color == that.color && super.equals(that)
    case _                   => false
  }
}

object ColoredPoint1 {

  def apply() = {
    val p = new Point1(1, 2)
    val cp = new ColoredPoint1(1, 2, Color.Blue)

    println("2: symmetric")
    println("p == cp <=> " + (p == cp))
    println("cp == p <=> " + (cp == p))
    println("--------------------------------------------------------------------------------")
  }
}