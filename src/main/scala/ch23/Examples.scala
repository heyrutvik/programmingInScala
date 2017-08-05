package ch23

object Examples {

  // later generators vary more rapidly than earlier one
  val res0 = for (x <- List(1, 2, 3); y <- List("one", "two", "three")) yield (x, y)

  def map[A, B](xs: List[A], f: A => B): List[B] = for (x <- xs) yield f(x)

  def flatMap[A, B](xs: List[A], f: A => List[B]): List[B] = {
    for (x <- xs; y <- f(x)) yield y
    //xs.flatMap(for (y <- f(_)) yield y)
    //xs.flatMap(f(_))
  }

  def withFilter[A](xs: List[A], p: A => Boolean): List[A] = {
    for (x <- xs; if p(x)) yield x
  }
}