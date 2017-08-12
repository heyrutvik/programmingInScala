package ch29

abstract class Browser {
  val database: Database
  def recipesUsing(food: Food) = database.allRecipes.map(r => r.ingredients.contains(food))
  def displayCategory(category: database.FoodCategory) = println(category)
}
