package ch29

object GoApple {
  def apply(dbName: Option[String] = None) = {
    val db: Database = dbName match {
      case Some("student") => StudentDatabase
      case _ => SimpleDatabase
    }
    object browser extends Browser {
      val database = db
    }
    val apple = db.foodNamed("Apple")
    apple match {
      case Some(a) =>
        for(recipe <- browser.recipesUsing(a)) println(recipe)
      case _ => throw new RuntimeException("Apple not found in database.")
    }
  }
}
