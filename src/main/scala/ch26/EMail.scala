package ch26

object EMail extends ((String, String) => String) {

  // The injection method
  override def apply(user: String, domain: String) = user + "@" + domain

  // The extraction method
  def unapply(s: String) = {
    println(s"EMail.unapply($s)")
    s split "@" match {
      case Array(u, d, _*) if ((u.length > 0 && d.length > 0)) =>
        Some((u, d))
      case _ => None
    }
  }
}
