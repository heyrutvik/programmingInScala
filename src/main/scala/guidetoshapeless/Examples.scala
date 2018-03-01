package guidetoshapeless

import shapeless.{::, Generic, HList, HNil}
import shapeless.ops.hlist.{IsHCons, Last}

trait Second[L <: HList] {
  type Out

  def apply(value: L): Out
}

object Second {
  type Aux[L <: HList, O] = Second[L] {type Out = O}

  def apply[L <: HList](implicit inst: Second[L]): Aux[L, inst.Out] = inst

  implicit def hlistSecond[A, B, Rest <: HList]: Aux[A :: B :: Rest, B] = {
    new Second[A :: B :: Rest] {
      override type Out = B

      override def apply(value: A :: B :: Rest): B = {
        value.tail.head
      }
    }
  }
}

object Examples extends App {

  def lastField[A, Repr <: HList](value: A)(
    implicit gen: Generic.Aux[A, Repr], last: Last[Repr]
  ): last.Out = last.apply(gen.to(value))

  def getWrappedValue1[A, H](value: A)(
    implicit gen: Generic.Aux[A, H :: HNil]
  ): H = gen.to(value).head

  /*
  def getWrappedValue2[A, Repr <: HList, Head, Tail <: HList](value: A)(
    implicit gen: Generic.Aux[A, Repr], ev: (Head :: Tail) =:= Repr
  ): Head = gen.to(value).head
   */

  def getWrappedValue[A, Repr <: HList, Head](value: A)(
    implicit gen: Generic.Aux[A, Repr], isHCons: IsHCons.Aux[Repr, Head, HNil]
  ): Head = gen.to(value).head

  val ic = IceCream("A1", 22, false)

  val last1 = Last[String :: Int :: Boolean :: HNil]
  println(last1(Generic[IceCream].to(ic)))

  val second1 = Second[String :: Int :: Boolean :: HNil]
  println(second1(Generic[IceCream].to(ic)))

  println(lastField(ic))

  case class Wrapper(value: Int)

  println(getWrappedValue(Wrapper(42)))
}
