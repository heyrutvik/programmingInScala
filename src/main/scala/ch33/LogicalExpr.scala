package ch33

import scala.util.parsing.combinator._

class LogicalExprGrammer extends JavaTokenParsers {

  val m = Map("1" -> "hello", "2" -> "world", "3" -> "silence")

  lazy val expr: Parser[String] = term ~ rest ^^ {
    case "(" ~ h ~ ")" ~ t => "(" + m.get(h.asInstanceOf[String]).getOrElse(h.asInstanceOf[String]) + ")" + t
    case h ~ t => {
      m.get(h.asInstanceOf[String]).getOrElse(h.asInstanceOf[String]) + t
    }
  }
  lazy val rest: Parser[String] = rep(and | or) ^^ {
    case ls => ls.mkString(" ")
  }
  lazy val and: Parser[String] = "and" ~ term ^^ {
    case _ ~ t => " and " + m.get(t.asInstanceOf[String]).getOrElse(t.asInstanceOf[String])
  }
  lazy val or: Parser[String] = "or" ~ term ^^ {
    case _ ~ t => " or " + m.get(t.asInstanceOf[String]).getOrElse(t.asInstanceOf[String])
  }
  lazy val term: Parser[Any] = "(" ~ expr ~ ")" | decimalNumber
}

// (new ch33.LogicalExpr()).apply("(1 and 2) or 3")
class LogicalExpr extends LogicalExprGrammer {

  def apply(input: String) = {
    parseAll(expr, input)
  }
}