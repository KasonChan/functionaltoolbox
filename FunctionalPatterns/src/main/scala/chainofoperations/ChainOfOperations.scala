package chainofoperations

/**
 * Created by kasonchan on 5/12/15.
 */
trait ChainOfOperations {

  /**
   * Book
   * A case class of book contains title, bookType and pages
   * @param title String
   * @param bookType String
   * @param pages Int
   */
  case class Book(title: String, bookType: String, pages: Int)

  /**
   * lovePages
   * Return the sum of the love book pages
   * @param books Seq[Book]
   * @return sum: Int
   */
  def lovePages(books: Seq[Book]): Int = {
    books filter((book) => book.bookType == "Love") map ((book) => book.pages) sum
  }

  /**
   * Initial
   * Return the initals of the name
   * @param name String
   * @return initials: String
   */
  def initials(name: String): String = {
    name.split(" ") map (_.toUpperCase) map (_.charAt(0)) mkString
  }

}
