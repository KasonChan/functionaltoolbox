package memoization

/**
 * Created by kasonchan on 5/15/15.
 */
trait Memoization {

  /**
   * Lookup
   * Expensive lookup for the id
   * @param id Int
   * @return String
   */
  def lookup(id: Int) = {
    // Simulate the time-consuming operation by having it sleep the thread
    Thread.sleep(1000)

    println(s"Doing expensive lookup for $id")
    Map(42 -> "foo", 12 -> "bar", 1 -> "baz").get(id)
  }

  /**
   * MemoizeLookup
   * First check its cache to see if it has results from a precious function
   * call. If it does, it returns the cached results.
   * Otherwise it calls the expensive lookup and caches the result before
   * returning them.
   * @return (Int) => Option[String]
   */
  def memoizeLookup: (Int) => Option[String] = {
    var cache = Map[Int, Option[String]]()

    (id: Int) =>
      cache.get(id) match {
        case Some(result: Option[String]) => result
        case None => {
          val result = lookup(id)
          cache += id -> result
          result
        }
      }
  }

}
