package guidetoshapeless

import CsvEncoder._

object CsvEncoderExample extends App {

  val employees: List[Employee] = List(
    Employee("Bill", 1, true),
    Employee("Peter", 2, false),
    Employee("Milton", 3, false)
  )

  val iceCreams: List[IceCream] = List(
    IceCream("A1", 1, true),
    IceCream("B1", 2, false),
    IceCream("C1", 3, false)
  )

  println(writeCsv(employees))
  println(writeCsv(iceCreams))
  println(writeCsv(employees zip iceCreams))

  val shapes: List[Shape] = List(Rectangle(10.0, 1.2), Circle(10.2))
  println(writeCsv(shapes))

  val tree: List[Tree[Int]] = List(Branch(Leaf(91), Leaf(11)))
  println(writeCsv(tree))
}

case class Employee(name: String, number: Int, manager: Boolean)
case class IceCream(name: String, price: Int, inCone: Boolean)

sealed trait Shape
case class Rectangle(width: Double, height: Double) extends Shape
case class Circle(radius: Double) extends Shape

sealed trait Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
case class Leaf[A](value: A) extends Tree[A]