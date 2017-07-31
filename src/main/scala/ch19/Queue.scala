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

trait Queue[T] {
  def head: T
  def tail: Queue[T]
  def enqueue(x: T): Queue[T]
}

object Queue {

  def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)

  private class QueueImpl[T](private val leading: List[T],
                             private val trailing: List[T])
      extends Queue[T] {

    def mirror = {
      if (leading.isEmpty)
        new QueueImpl(trailing.reverse, Nil)
      else
        this
    }

    def head: T = mirror.leading.head

    def tail: QueueImpl[T] = {
      val q = mirror
      new QueueImpl(q.leading.tail, q.trailing)
    }

    def enqueue(x: T) = new QueueImpl(leading, x :: trailing)
  }
}