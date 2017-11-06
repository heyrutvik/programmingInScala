package ch29

object GoApple {
  def apply(dbName: String) = {
    val db: Database = Option(dbName) match {
      case Some("student") => StudentDatabase
      case _ => SimpleDatabase
    }
    object browser extends Browser {
      val database: db.type = db
    }
    for(category <- db.allCategories) browser.displayCategory(category)
    val apple = db.foodNamed("Apple")
    apple match {
      case Some(a) =>
        for(recipe <- browser.recipesUsing(a)) println(recipe)
      case _ => throw new RuntimeException("Apple not found in database.")
    }
  }
}
