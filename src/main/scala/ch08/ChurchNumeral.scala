package ch08

object ChurchNumeral {

  /**
   * arbitrary function which maps A to A
   */
  type Fun[A] = A => A

  /**
   * number {takes a function and input} x, applies that function to input that number times!!
   */
  type Num[A] = Fun[A] => A => A

  /**
   * representation of zero as a function applies to x nil times, returns x itself.
   */
  def zero[A]: Num[A] = (f: Fun[A]) => (x: A) => x

  /**
   * representation of successor of number as a function that maps number to number.
   * let's find successor of zero by applying add1(zero).
   * add1(zero) returns procedure which {takes a function and input} which has body f(n(f)(x)) where,
   *   number n = zero
   *   function f = inc
   *   input x = 0
   * by replacing body with values we get inc(zero(inc)(0)),
   * zero(inc) returns procedure which takes input x and returns x,
   * because zero doesn't apply function to its input!
   * so, zero(inc)(0) return 0
   * replacing zero(inc)(0) in inc(zero(inc)(0)) with 0 we get inc(0) which is 1. successor of 0.
   */
  def add1[A](n: Num[A]): Num[A] = (f: Fun[A]) => (x: A) => f(n(f)(x))

  /**
   * representation of summation of two numbers as function that maps two numbers to number.
   * let's find sum of one and two by applying sum(one, two).
   * sum(one, two) returns procedure which {takes a function and input} which has body n(f)(m(f)(x)) where,
   *   number n = one
   *   number m = two
   *   function f = inc
   *   input x = 0
   * by replacing body with values we get one(inc)(two(inc)(0)),
   * two(inc) returns a procedure which takes input x and returns inc applied to x twice,
   * two(inc)(0) means inc(inc(0)) = 2.
   * replacing two(inc)(0) in body one(inc)(two(inc)(0)) with 2 we get one(inc)(2).
   * one(inc)(2) means applying inc to 2 one time.. inc(2) = 3 which is sum of 1 and 2.
   */
  def sum[A](n: Num[A], m: Num[A]): Num[A] = (f: Fun[A]) => (x: A) => n(f)(m(f)(x))

  /**
    * representation of multiplication of two numbers as function that maps two numbers to number.
    * let's find mul of two and three by applying mul(two, three).
    * mul(two, three) returns procedure which {takes a function and input} which has body n(m(f))(x) where,
    *   number n = two
    *   number m = three
    *   function f = inc
    *   input x = 0
    * by replacing body with values we get two(three(inc))(0),
    * three(inc) returns a procedure which takes input x and returns inc applied to x thrice,
    * two(three(inc)) returns a procedure which takes input x and
    * returns thrice function applied twice to input x.
    * two(three(inc))(0) evaluate six times inc to 0 which is 6. multiplication of 2 and 3.
    */
  def mul[A](n: Num[A], m: Num[A]): Num[A] = (f: Fun[A]) => (x: A) => n(m(f))(x)

  def inc(n: Int) = n + 1

  def inc(s: String) = s.mkString("<", "", ">")

  val one = add1[Int](zero)

  val two = add1[Int](one)

  val three = sum[Int](one, two)

  val four = mul[Int](two, two)

  val print = List(
    s"$zero = ${zero[Int](inc)(0)}",
    s"$one = ${one(inc)(0)}",
    s"$two = ${two(inc)(0)}",
    s"$three = ${three(inc)(0)}",
    s"$four = ${four(inc)(0)}"
  ).mkString("\n")
}
