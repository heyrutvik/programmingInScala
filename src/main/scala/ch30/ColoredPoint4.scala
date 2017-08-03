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
 *      - modify equals of Point to check run-time type and remove general case of sub-type (ColoredPoint3)
 *    -> canEqual has solution
 *      - if sub class overrides equals method, it should have to define canEqual method
 *      - canEqual checks for "other object can equals for this"
 *      - check ColoredPoint4
 */

class ColoredPoint4(x: Int, y: Int, val color: Color.Value) extends Point2(x, y) {

  override def equals(other: Any) = other match {
    case that: ColoredPoint4 => (that canEquals this) && color == that.color && super.equals(that)
    case _                   => false
  }

  override def canEquals(other: Any) = other.isInstanceOf[ColoredPoint4]
}

object ColoredPoint4 {

  def apply() = {
    val p = new Point2(1, 2)
    val cp = new ColoredPoint4(1, 2, Color.Violet)
    val redp = new ColoredPoint4(1, 2, Color.Red)
    val bluep = new ColoredPoint4(1, 2, Color.Blue)

    println("2: symmetric")
    println("p == cp <=> " + (p == cp))
    println("cp == p <=> " + (cp == p))
    println

    println("3: transitive")
    println("redp == p <=> " + (redp == p))
    println("p == bluep <=> " + (p == bluep))
    println("redp == bluep <=> " + (redp == bluep))
    println

    println("now, Anon class object works beacuse canEqual inherit from Point")

    val pAnon = new Point2(1, 1) { override val y = 2 }
    val coll = List(p)
    println("coll contains p <=> " + (coll contains p))
    println("coll contains bluep <=> " + (coll contains bluep))
    println("coll contains pAnon <=> " + (coll contains pAnon))
    println("--------------------------------------------------------------------------------")

  }
}