package ch26

trait User {
  val name: String
  val age: Int
}

class FreeUser(val name: String, val age: Int) extends User
object FreeUser {
  def unapply(arg: FreeUser): Option[(String, Int)] = Option(arg.name, arg.age)
}
class PaidUser(val name: String, val age: Int) extends User
object PaidUser {
  def unapply(arg: PaidUser): Option[(String, Int)] = Option(arg.name, arg.age)
}
object IsRutvik {
  def unapply(arg: FreeUser): Boolean = if (arg.name == "rutvik") true else false
}

object User {

  def printName(u: FreeUser) = println(u.name)

  val u: User = new FreeUser("rutvik", 25)

  def test = {
    u match {
      /**
        *   x @ BooleanExtractor()
        *   where type of x will be type of BooleanExtractor's unapply methods param's type
        *
        *   freeuser's type is FreeUser because IsRutvik() takes FreeUser,
        *   change it to User for experiment
        */
      case freeuser @ IsRutvik() => printName(freeuser)
      case _ => println("not rutvik")
    }
  }

  val FreeUser(name, age) = u.asInstanceOf[FreeUser]

  /**
    *   can throw MatchError is extractor fails
    */
  val check @ IsRutvik() = u.asInstanceOf[FreeUser]
}