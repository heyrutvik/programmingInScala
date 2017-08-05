package ch21

import javax.swing.JButton

object Examples {

  /*
   ********************************************************************* 
   * 	Note: this one does not need implicit conversion,
   * 	scala 2.12 supports Single Abstract Method (SAM) to work directly
   * 
   * 	otherwise,
   * 	we have to define function which take another function `f` 
   * 	which implements abstract method of ActionListner interface
   *********************************************************************
   */

  val button = new JButton
  button.addActionListener {
    _ => println("Oops!")
  }

  // context bounds
  def maxList[T: Ordering](elements: List[T]): T = {
    elements match {
      case Nil      => throw new IllegalArgumentException("empty list!")
      case x :: Nil => x
      case x :: xs =>
        val maxRest = maxList(xs)
        if (implicitly[Ordering[T]].gt(x, maxRest)) x
        else maxRest
    }
  }
}