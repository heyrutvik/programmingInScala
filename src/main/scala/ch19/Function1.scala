package ch19

trait Function1[-S, +T] {
  def apply(x: S): T
}

class Publication(val title: String)
class Book(title: String) extends Publication(title)

object Library {

  val books: Set[Book] = {
    Set(new Book("Programming in Scala"), new Book("Programming in C"))
  }

  def printBookList(info: Function1[Book, AnyRef]) = {
    for (book <- books) println(info(book))
  }
}

object Customer {

  def apply() = {
    def getTitle = new Function1[Publication, String] {
      def apply(p: Publication): String = {
        p.title
      }
    }
    Library.printBookList(getTitle)
  }
}