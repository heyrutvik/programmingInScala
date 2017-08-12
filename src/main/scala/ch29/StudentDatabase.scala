package ch29

object StudentDatabase extends Database with StudentFoods with StudentRecipe

trait StudentFoods {
  object FrozenFood extends Food("FrozenFood")
  def allFoods = List(FrozenFood)
  def allCategories = Nil
}

trait StudentRecipe { this: StudentFoods =>
  object HeatItUp extends Recipe(
    "heat it up",
    List(FrozenFood),
    "Microwave the 'food' for 10 minutes"
  )
  def allRecipes = List(HeatItUp)
}