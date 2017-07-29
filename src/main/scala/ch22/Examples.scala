package ch22

import scala.collection.mutable.ListBuffer

object Examples {

  /*
   * 	not tail recursive
   * 	chances for stack-overflow 
   */

  def incAll(xs: List[Int]): List[Int] = xs match {
    case Nil    => Nil
    case h :: l => h + 1 :: incAll(l)
  }

  /*
   * 	efficient way to build
   */
  def incAll1(xs: List[Int]): List[Int] = {
    val buf = new ListBuffer[Int]
    for (x <- xs)
      buf += x + 1
    buf.toList
  }
}