package ch15

object Express {

  val f = new ExprFormatter

  val e0 = BinOp("/", Number(1), Number(2))
  val e1 = BinOp("*", e0, BinOp("+", Number(15), Number(5)))
  val e2 = BinOp("+", BinOp("*", Var("y"), Number(21)), BinOp("-", Var("x"), Var("y")))
  val e3 = BinOp("/", e1, e2)

  private def show(e: Expr) = println("\n" + f.format(e) + "\n")

  def print() = {
    for (e <- Array(e0, e1, e2, e3)) show(e)
  }
}