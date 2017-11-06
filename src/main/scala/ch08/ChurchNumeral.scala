package ch08

object ChurchNumeral {

  type Fun[A] = A => A

  type Num[A] = Fun[A] => Fun[A]

  def zero[A]: Num[A] = (f: Fun[A]) => (x: A) => x

  def add1[A](n: Num[A]): Num[A] = (f: Fun[A]) => (x: A) => f(n(f)(x))

  def sum[A](n: Num[A], m: Num[A]): Num[A] = (f: Fun[A]) => (x: A) => n(f)(m(f)(x))

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
