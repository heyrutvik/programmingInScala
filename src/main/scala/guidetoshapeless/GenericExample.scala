package guidetoshapeless

import shapeless.Generic

case class Person(name: String, age: Int)
case class IceCream(name: String, price: Int)

sealed trait Option[+A]
case class Some[A](value: A) extends Option[A]
case object None extends Option[Nothing]

object GenericExample extends App {

  val genPerson = Generic[Person]
  val personGeneric = genPerson.to(Person("Rutvik", 26))

  val genIcecream = Generic[IceCream]
  val icecreamGeneric = genIcecream.to(IceCream("Kesar", 150))

  val iceCreamToPerson = genPerson.from(icecreamGeneric)

  val coproductGeneric = Generic[Option[Int]]
  val someGeneric = Generic[Some[Int]]
}
