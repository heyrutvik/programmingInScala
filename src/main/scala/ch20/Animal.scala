package ch20

class Food

abstract class Animal {
  type SuitableFood <: Food
  def eat(food: SuitableFood) = println(food)
}

class Grass extends Food
class Cow extends Animal {
  type SuitableFood = Grass
}

class DogFood extends Food
class Dog extends Animal {
  type SuitableFood = DogFood
}

object Animal {

  /*
   ********************************************************************************
   *	Note:
   * 	bessy.SuitableFood is subtype of Cow#SuitableFood
   * 
   *	bessy.SuitableFood is path-dependant type, depends on outer object
   * 	Cow#SuitableFood is inner class type, depends on outer class
   * 
   * 	we can create instance of bessy.SuitableFood because instance of outer class
   * 	we can not create install of Cow#SuitableFood becuase it does not name any
   *  specific instance of outer
   ******************************************************************************** 
   */

  def apply() = {
    val bessy = new Cow
    val bessyFood: Cow#SuitableFood = new bessy.SuitableFood
    bessy.eat(bessyFood)
  }
}