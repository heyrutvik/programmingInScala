package ch26

object Examples {

  def userTwiceUpper(s: String) = s match {
    case EMail(Twice(x@UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ => "no match"
  }

  def isTominDotCom(s: String): Boolean = s match {
    case EMail("tom", Domain("com", _*)) => true
    case _ => false
  }
}
