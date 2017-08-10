package ch26

object UpperCase {

  def unapply(s: String): Boolean = {
    println(s"UpperCase.unapply($s)")
    s.toUpperCase == s
  }
}