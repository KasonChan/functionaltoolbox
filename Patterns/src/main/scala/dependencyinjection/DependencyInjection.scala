package dependencyinjection

/**
 * Created by kasonchan on 4/19/15.
 */
trait DependencyInjection {

  case class Book(bookId: String, title: String)

  case class Page(bookId: String)

  case class DecoratedBook(book: Book, page: Page)

  //  Define some traits as interfaces for dependencies
  trait BookDaoComponent {

    trait BookDao {
      def getBook(id: String): Book
    }

  }

  trait FavouritesServiceComponent {

    trait FavouritesService {
      def getFavouritePages(id: String): Vector[Page]
    }

  }

  //  Stub out to return static responses by implementing the interfaces
  trait BookDaoComponentImpl extends BookDaoComponent {

    class BookDaoImpl extends BookDao {
      def getBook(id: String): Book = new Book("1", "A")
    }

  }

  trait FavouritesServiceComponentImpl extends FavouritesServiceComponent {

    class FavouritesServiceImpl extends FavouritesService {
      def getFavouritePages(id: String): Vector[Page] = Vector(Page("1"))

      def getFavouriteBooks(id: String): Vector[Page] = Vector(new Page("1"))
    }

  }

  trait BookServiceComponentImpl {

    // Self-type annotation ensures whenver mixed into an object or class,
    // this reference of that object has the type of below
    this: BookDaoComponent with FavouritesServiceComponent =>

    val favouritesService: FavouritesService
    val bookDao: BookDao

    class BookServiceImpl {
      def getFavouriteDecoratedBooks(userId: String): Vector[DecoratedBook] =
        for (
          favouriteBook <- favouritesService.getFavouritePages(userId);
          val book = bookDao.getBook(favouriteBook.bookId)
        ) yield DecoratedBook(book, favouriteBook)
    }

  }

  object ComponentRegistry extends BookServiceComponentImpl
  with FavouritesServiceComponentImpl with BookDaoComponentImpl {

    val favouritesService = new FavouritesServiceImpl
    val bookDao = new BookDaoImpl

    val bookService = new BookServiceImpl
  }

}


