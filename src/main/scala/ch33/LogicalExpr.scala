package ch33

import scala.util.parsing.combinator._

class LogicalExprGrammer extends JavaTokenParsers {

  val m = Map("1" -> "x > 2", "2" -> "y <= 50", "3" -> "z == false")

  lazy val expr: Parser[String] = term ~ rest ^^ {
    case h ~ t => h + t
  }
  lazy val rest: Parser[String] = rep(and | or) ^^ {
    case ls => ls.mkString(" ")
  }
  lazy val and: Parser[String] = "and" ~ term ^^ {
    case _ ~ t => " and " + t
  }
  lazy val or: Parser[String] = "or" ~ term ^^ {
    case _ ~ t => " or " + t
  }
  lazy val term: Parser[String] = "(" ~ expr ~ ")" ^^ {
    case "(" ~ h ~ ")" => "(" + h + ")"
  } | decimalNumber ^^ {
    case k => m(k)
  }
}

// (new ch33.LogicalExpr()).apply("(1 and 2) or 3")
class LogicalExpr extends LogicalExprGrammer {

  def apply(input: String) = {
    parseAll(expr, input)
  }
}