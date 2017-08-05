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
}