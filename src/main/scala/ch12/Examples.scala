package ch12

object Examples {

  def apply() = {
    object x extends A with C with B
    println(x)
  }
}

trait A {
  override def toString = "A"
}

trait B {
  override def toString = "B"
}

trait C {
  override def toString = "C"
}