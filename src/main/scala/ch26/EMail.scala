package ch26

object EMail extends ((String, String) => String) {

  // The injection method
  override def apply(user: String, domain: String) = user + "@" + domain

  // The extraction method
  def unapply(s: String) = {
    println(s"EMail.unapply($s)")
    val parts = s split "@"
    if (parts.size == 2) Some((parts(0), parts(1))) else None
  }
}
