package ch29

/*

StudentDatabase ->
StudentRecipe ->
StudentFoods ->
StudentCategories ->
Database ->
FoodCategories ->
AnyRef ->
Any

 */
object StudentDatabase extends Database with StudentCategories with StudentFoods with StudentRecipe

trait StudentFoods {
  object FrozenFood extends Food("FrozenFood")
  def allFoods = List(FrozenFood)
}

trait StudentRecipe { this: StudentFoods =>
  object HeatItUp extends Recipe(
    "heat it up",
    List(FrozenFood),
    "Microwave the 'food' for 10 minutes"
  )
  def allRecipes = List(HeatItUp)
}

trait StudentCategories extends FoodCategories { this: StudentFoods =>
  def allCategories = List(FoodCategory("edible", List(FrozenFood)))
}

//trait StudentCategories { this: FoodCategories with StudentFoods =>
//  def allCategories = List(FoodCategory("edible", List(FrozenFood)))
//}