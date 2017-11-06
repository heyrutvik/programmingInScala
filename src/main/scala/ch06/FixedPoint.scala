package src.main.scala.ch06

// f(x) = x
object FixedPoint {
  
  def forCos = apply(Math.cos, 1.0)
  
  def sqrt(x: Double) = apply(averageDamp(y => x / y), 1.0)
  
  def apply(f: Double => Double, guess: Double) = {
    def loop(guess: Double): Double = {
      val nextGuess = f(guess)
      if (closeEnough(nextGuess, guess)) nextGuess
      else loop(nextGuess)
    }
    loop(guess)
  }
  
  private def averageDamp(f: Double => Double): Double => Double = { x: Double =>
    (x + f(x)) / 2
  }
  
  private def closeEnough(a: Double, b: Double) = Math.abs(a - b) < 0.000001
}