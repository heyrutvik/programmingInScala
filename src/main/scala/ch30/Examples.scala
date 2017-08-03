package ch30

object Examples {

  def apply() = {
    val note = s"""
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
      """
    println(note)
    ColoredPoint1()
    ColoredPoint2()
    ColoredPoint3()
    ColoredPoint4()
  }
}