package ch23

object NQueensProblem {

  private type Position = (Int, Int)
  private type Solution = List[Position]

  def apply(n: Int) = println(draw(n, placeQueens(n)).mkString("\n\n"))

  private def placeQueens(n: Int): List[Solution] = {
    def loop(k: Int): List[Solution] = {
      if (k == 0) List(Nil)
      else {
        for {
          queens <- loop(k - 1)
          column <- 1 to n
          queen = (k, column)
          if isSafe(queen, queens)
        } yield queen :: queens
      }
    }

    def isSafe(queen: Position, queens: List[Position]): Boolean = {
      queens forall (q => !inCheck(queen, q))
    }

    def inCheck(q1: Position, q2: Position): Boolean = {
      q1._1 == q2._1 ||
        q1._2 == q2._2 ||
        (q1._1 - q2._1).abs == (q1._2 - q2._2).abs
    }

    loop(n)
  }

  private def draw(n: Int, qs: List[Solution]): Seq[String] = {
    def board(s: Solution): Seq[String] = {
      (1 to n).map { row =>
        (1 to n).map { column =>
          if (s contains (row, column)) '*'
          else '_'
        }.mkString("|", " ", "|")
      }
    }

    for (q <- qs) yield board(q).mkString("\n")
  }
}
