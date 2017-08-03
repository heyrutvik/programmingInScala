package ch06

class Rational(n: Int, d: Int) {

  require(d != 0, "second param must be non-zero")

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  override def toString = s"$numer / $denom"

  def canEqual(other: Any): Boolean = other.isInstanceOf[Rational]

  override def equals(other: Any): Boolean = other match {
    case that: Rational =>
      (that canEqual this) &&
        numer == that.numer &&
        denom == that.denom
    case _ => false
  }

  def +(that: Rational): Rational = {
    new Rational(
      numer * that.denom + denom * that.numer,
      denom * that.denom)
  }

  def +(i: Int): Rational = {
    new Rational(numer + i * denom, denom)
  }

  def *(that: Rational): Rational = {
    new Rational(numer * that.numer, denom * that.denom)
  }

  def *(i: Int): Rational = {
    new Rational(numer * i, denom)
  }

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}

object Rational {

  implicit def intToRational(x: Int) = new Rational(x)
}