package guidetoshapeless

import shapeless.{Generic, HNil, Inl, Inr}

case class Person(name: String, age: Int)
case class AnyThingElse(name: String, price: Int)

sealed trait Option[+A]
case class Some[A](value: A) extends Option[A]
case object None extends Option[Nothing]

object GenericExample extends App {

  val genPerson = Generic[Person]
  val personGeneric = genPerson.to(Person("Rutvik", 26))

  val genIcecream = Generic[AnyThingElse]
  val icecreamGeneric = genIcecream.to(AnyThingElse("Kesar", 150))

  val iceCreamToPerson = genPerson.from(icecreamGeneric)

  val coproductGeneric = Generic[Option[Int]]
  val a = coproductGeneric.to(Some(10))
  val b = coproductGeneric.to(None)

  val someGeneric = Generic[Some[Int]]
}
