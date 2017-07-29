package ch22

import scala.collection.mutable.ListBuffer

sealed abstract class RList[+T] {

  def isEmpty: Boolean
  def head: T
  def tail: RList[T]

  def length: Int =
    if (isEmpty) 0
    else 1 + tail.length

  def drop(n: Int): RList[T] =
    if (isEmpty) End
    else if (n <= 0) this
    else tail.drop(n - 1)

  def ::[U >: T](x: U): RList[U] = new ch22.Cons(x, this)

  def map[U](f: T => U): RList[U] =
    if (isEmpty) End
    else f(head) :: tail.map(f)

  def :::[U >: T](prefix: RList[U]): RList[U] =
    if (prefix.isEmpty) this
    else prefix.head :: prefix.tail ::: this
}

case object End extends RList[Nothing] {

  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: Nothing = throw new NoSuchElementException("tail of empty list")
}

final case class Cons[T](head: T, tail: RList[T]) extends RList[T] {
  def isEmpty = false
}