package ch06

import scala.annotation.tailrec

object BisectionMethod {
  
  def forSin(a: Double, b: Double) = apply(Math.sin, a, b)
  
  def apply(f: Double => Double, a: Double, b: Double): Option[Double] = {
    val fa = f(a)
    val fb = f(b)
    if (isNegative(fa) && isPositive(fb)) Some(search(f, a, b))
    else if (isPositive(fa) && isNegative(fb)) Some(search(f, b, a))
    else None
  }
  
  @tailrec
  private def search(f: Double => Double, negPoint: Double, posPoint: Double): Double = {
    val midPoint = average(negPoint, posPoint)
    if (closeEnough(negPoint, posPoint)) midPoint
    else {
      val testValue = f(midPoint)
      if (isPositive(testValue)) search(f, negPoint, midPoint)
      else if (isNegative(testValue)) search(f, midPoint, posPoint)
      else midPoint
    }
  }
  
  private def average(a: Double, b: Double) = (a + b) / 2.0
  
  private def closeEnough(a: Double, b: Double) = Math.abs(a - b) < 0.0001
  
  private def isPositive(x: Double) = x > 0
  
  private def isNegative(x: Double) = x < 0
}
