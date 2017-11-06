package ch29

/*

SimpleDatabase ->
SimpleRecipe ->
SimpleFoods ->
Database ->
FoodCategories ->
AnyRef ->
Any

 */

object SimpleDatabase extends Database with SimpleFoods with SimpleRecipe

trait SimpleFoods {
  object Apple extends Food("Apple")
  object Pear extends Food("Pear")
  def allFoods = List(Apple, Pear)
  def allCategories = Nil
}

trait SimpleRecipe { this: SimpleFoods =>
  object FruitSalad extends Recipe(
    "fruit salad",
    List(Apple, Pear),
    "Mix it all together"
  )
  def allRecipes = List(FruitSalad)
}
