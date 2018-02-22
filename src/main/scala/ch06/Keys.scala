package ch06

trait Keys {
  type Key
  def key(s: String): Key
}

object HashKeys extends Keys {
  override type Key = Int
  override def key(s: String): Int = s.hashCode
}

object Test {
  def mapKeys(k: Keys, ss: List[String]): List[k.Key] = ss.map(k.key(_))
}
