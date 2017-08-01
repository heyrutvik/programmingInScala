package ch19

case class Person(firstName: String, lastName: String)
    extends Ordered[Person] {

  def compare(that: Person) = {
    val lastNameComp = lastName.compareToIgnoreCase(that.lastName)
    if (lastNameComp != 0) lastNameComp
    else firstName.compareToIgnoreCase(that.firstName)
  }

  override def toString = firstName + " " + lastName
}