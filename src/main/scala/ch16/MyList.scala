package ch16

object MyList {

  def append[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil     => ys
    case x :: xs => x :: append(xs, ys)
  }

  def reverse[T](xs: List[T]): List[T] = xs match {
    case Nil     => xs
    case x :: xs => reverse(xs) ::: List(x)
  }

  def msort[T](compare: (T, T) => Boolean)(xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (compare(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(compare)(ys), msort(compare)(zs))
    }
  }

  def flattenLeft[T](xss: List[List[T]]) = (List[T]() /: xss)(_ ::: _)

  def flattenRight[T](xss: List[List[T]]) = (xss :\ List[T]())(_ ::: _)

  def reverseLeft[T](xs: List[T]) = (List[T]() /: xs)((xs, x) => x :: xs)
}