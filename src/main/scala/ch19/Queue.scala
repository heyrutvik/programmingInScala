package ch19

/*
 * 	information hiding
 * 	1) make constructor private and define factory method
 * 		-> with auxiliary constructor
 * 		-> companion object's apply method
 * 	presented here, private class
 * 		-> reveals public interface using Trait
 * 		-> private inner class with implemented interfaces
 * 		-> apply method for creating Queue type
 */

/*
 *	S    -> T
 * 	Q[S] -> Q[T]
 */
trait Queue[+T] {
  def head: T
  def tail: Queue[T]
  def enqueue[U >: T](x: U): Queue[U]
}

object Queue {

  def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)

  private class QueueImpl[T](private[this] var leading: List[T],
                             private[this] var trailing: List[T])
      extends Queue[T] {

    def mirror() = {
      if (leading.isEmpty) {
        while (!trailing.isEmpty) {
          leading = trailing.head :: leading
          trailing = trailing.tail
        }
      }
    }

    def head: T = {
      mirror()
      leading.head
    }

    def tail: QueueImpl[T] = {
      mirror()
      new QueueImpl(leading.tail, trailing)
    }

    def enqueue[U >: T](x: U): QueueImpl[U] = new QueueImpl(leading, x :: trailing)
  }
}