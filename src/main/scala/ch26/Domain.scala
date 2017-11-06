package ch26

object Domain {

  def apply(parts: String*): String = parts.reverse.mkString(".")

  def unapplySeq(whole: String): Option[Seq[String]] = {
    println(s"Domain.unapplySeq($whole)")
    Some(whole.split("\\.").reverse)
  }
}
