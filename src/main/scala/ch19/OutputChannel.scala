package ch19

/*
 * 	S    <- T
 * 	O[S] -> O[T]
 */
trait OutputChannel[-T] {
  def write(x: T)
}