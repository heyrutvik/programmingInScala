package guidetoshapeless

trait Eq[A] {
  def ==(a: A, b: A): Boolean
  def /=(a: A, b: A): Boolean
}

object Eq {
  implicit val booleanEq = new Eq[Boolean] {
    override def ==(a: Boolean, b: Boolean): Boolean = a == b
    override def /=(a: Boolean, b: Boolean): Boolean = a != b
  }
  implicit val stringEq = new Eq[String] {
    override def ==(a: String, b: String): Boolean = a == b
    override def /=(a: String, b: String): Boolean = a != b
  }
  implicit val intEq = new Eq[Int] {
    override def ==(a: Int, b: Int): Boolean = a == b
    override def /=(a: Int, b: Int): Boolean = a != b
  }
  implicit val doubleEq = new Eq[Double] {
    override def ==(a: Double, b: Double): Boolean = a == b
    override def /=(a: Double, b: Double): Boolean = a != b
  }
  implicit def pairEq[A, B](implicit aeq: Eq[A], beq: Eq[B]) = new Eq[(A, B)] {
    override def ==(a: (A, B), b: (A, B)): Boolean = aeq.==(a._1, b._1) && beq.==(a._2, b._2)
    override def /=(a: (A, B), b: (A, B)): Boolean = aeq./=(a._1, b._1) && beq./=(a._2, b._2)
  }
}

object TypeClassExample {

  def isEqual[A](a: A, b: A)(implicit eq: Eq[A]): Boolean = {
    eq.==(a, b)
  }

  def notEqual[A](a: A, b: A)(implicit eq: Eq[A]): Boolean = {
    eq./=(a, b)
  }
}
