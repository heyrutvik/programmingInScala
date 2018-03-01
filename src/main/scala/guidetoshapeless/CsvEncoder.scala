package guidetoshapeless

import shapeless.{:+:, ::, CNil, Coproduct, Generic, HList, HNil, Inl, Inr, Lazy}

trait CsvEncoder[A] {
  def encode(value: A): List[String]
}

object CsvEncoder {

  def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc

  def instance[A](func: A => List[String]): CsvEncoder[A] = { value =>
    func(value)
  }

  def getRepr[A : Generic](value: A) = implicitly[Generic[A]].to(value)

  // case class
  implicit val employeeEncoder: CsvEncoder[Employee] = { value =>
    List(value.name, value.number.toString, value.manager.toString)
  }

  // case class
  implicit val iceCreamEncode: CsvEncoder[IceCream] = { value =>
    List(value.name, value.price.toString, value.inCone.toString)
  }

  // pair of case class
  implicit def pairEncoder[A, B](implicit ae: CsvEncoder[A], be: CsvEncoder[B]): CsvEncoder[(A, B)] = { pair =>
    val (a, b) = pair
    ae.encode(a) ++ be.encode(b)
  }

  // basic types
  implicit val stringEncoder: CsvEncoder[String] = { value =>
    List(value)
  }

  implicit val intEncoder: CsvEncoder[Int] = { value =>
    List(value.toString)
  }

  implicit val booleanEncoder: CsvEncoder[Boolean] = { value =>
    List(value.toString)
  }

  implicit val doubleEncoder: CsvEncoder[Double] = { value =>
    List(value.toString)
  }

  // generic types
  implicit val hnilEncoder: CsvEncoder[HNil] = { value =>
    Nil
  }

  implicit def hlistEncoder[H, T <: HList](implicit he: Lazy[CsvEncoder[H]], te: CsvEncoder[T]): CsvEncoder[H :: T] = instance {
    case h :: t => he.value.encode(h) ++ te.encode(t)
  }

  implicit def productEncoder[A, R](implicit gen: Generic[A] {type Repr = R}, enc: Lazy[CsvEncoder[R]]): CsvEncoder[A] = { value =>
    enc.value.encode(gen.to(value))
  }

  implicit val cnilEncoder: CsvEncoder[CNil] = { value =>
    throw new Exception("Inconceivable")
  }

  implicit def coproductEncoder[H, T <: Coproduct](implicit he: Lazy[CsvEncoder[H]], te: CsvEncoder[T]): CsvEncoder[H :+: T] = instance {
    case Inl(h) => he.value.encode(h)
    case Inr(t) => te.encode(t)
  }

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(v => enc.encode(v).mkString(", ")).mkString("\n")
}