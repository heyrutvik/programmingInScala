package ch19

object UpperBound {

  def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xt, y :: yt) =>
          if (x < y) x :: merge(xt, ys)
          else y :: merge(xs, yt)
      }
    }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (left, right) = xs splitAt n
      merge(orderedMergeSort(left), orderedMergeSort(right))
    }
  }
}