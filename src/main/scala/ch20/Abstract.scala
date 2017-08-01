package ch20

trait Abstract {
  type T
  def transform(x: T): T
  val initial: T
  var current: T
}

class Concrete extends Abstract {
  type T = String
  def transform(x: T) = s"transform: $x"
  val initial = "Hi"
  var current = initial
}

/*
 ******************************************************* 
 * 	Note:
 * 	abstract `val` can't be override with `def`
 * 	becuase `val` is meant to be constant
 * 	on the other hand, `def` can be override with `val`
 *******************************************************
 */

/*
 * 	TOPIC: Initialize abstract val
 * 	commented class `A` will print 0 for y
 * 	class `A` uses pre-initialized field
 */

trait X {
  val x: Int
  val y = x
}

//class A extends X {
//  val x = 1
//  println(y)
//}

class A extends {
  val x = 1
} with X {
  println(y)
}

object X {
  def apply() = new A
}

/*
 * 	class `B` uses lazy val
 */

trait Y {
  val x: Int
  lazy val y = x
}

class B extends Y {
  val x = 1
  println(y)
}

object Y {
  def apply() = new B
}