package demo

import chainofoperations.ChainOfOperations
import filtermapreduce.FilterMapReduce
import focusedmutability.FocusedMutability
import functionbuilder.FunctionBuilder
import memoization.Memoization

/**
 * Created by kasonchan on 5/11/15.
 */
object Demo extends FilterMapReduce with ChainOfOperations with
FunctionBuilder with Memoization with FocusedMutability {

  def main(args: Array[String]) {

    /**
     * Filter-Map-Reduce
     */
    val prices = Vector(5.0, 30.0, 20.5, 3.5, 1.5, 50.0)

    // Calculate total discount with anonymous function
    val totalDiscount1 = calculateTotalDiscount(prices)
    println(totalDiscount1) // 10.05

    // Calculate total discount with named function
    val totalDiscount2 = calculateTotalDiscountNamed(prices)
    println(totalDiscount2) // 10.05

    /** ***********************************************************************/

    /**
     * Chain of operations
     */
    val book1 = Book("Big Data", "Computer Science", 306)
    val book2 = Book("Me Without You", "Love", 96)
    val book3 = Book("Where Loves Find You", "Love", 160)
    val book4 = Book("Functional Programming Patterns in Scala and Clojure",
      "Computer Science", 232)

    val books = Seq(book1, book2, book3, book4)

    // Print total love pages
    val totalLovePages = lovePages(books)
    println(totalLovePages) // 256

    val person1 = "Ka Son Chan"
    val person2 = "Ieng Teng Chan"

    // Print the initials
    println(initials(person1)) // KSC
    println(initials(person2)) // ITC

    /** ***********************************************************************/

    /**
     * Function builder
     */
    // Print odd or even
    println(odd(9)) // true
    println(even(10)) // true
    println(even(9)) // false

    // Print discounts
    println(discount(50)(200)) // 100.0
    println(discount(100)(200)) // 0.0

    // Create a 45% discount
    val fortyFivePercentOff = discount(45)

    // Print the sum of the sequence after 45% discounts
    println(Seq(100.0, 25.0, 50.0, 25.5) map fortyFivePercentOff sum) // 110.275

    // Print discounts
    println(discount2(50)(200)) // Some(100.0)
    println(discount2(100)(200)) // Some(0.0)
    println(discount2(150)(200)) // None

    // Print foo
    println(foo(3)("Hello")) // Hello
    println(foo(4)("World")) // World2

    /**
     * Map key selector
     */
    val person = Map('name -> Map('first -> "Kason", 'last -> "Chan"),
      'address -> Map('number -> 123, 'street -> "Not sure"))

    val coupons = Map('savings -> Map('percentOff -> 5.0, 'types -> Seq()),
      'description -> "Save 5% on Industrial Supply Items",
      'merchant -> Map('name -> "Pricefalls",
        'slug -> "pricefalls",
        'bpcSlug -> "pricefalls"))

    // Print middle name
    val middle = selector('name, 'middle)
    println(middle(person)) // None

    // Print street
    val street = selector('address, 'street)
    println(street(person)) // Some(Not sure)

    // Print the description
    val description = selector('description)
    println(description(coupons)) // Some(Save 5% on Industrial Supply Items)

    // Print amountOff
    val amountOff = selector('savings, 'amountOff)
    println(amountOff(coupons)) // None

    // Print types
    val types = selector('savings, 'types)
    println(types(coupons)) // Some(List())

    /**
     * Function composition
     */
    val concatZYX1 = concatX compose concatY compose concatZ
    val concatZYX2 = concatZ andThen concatY andThen concatX

    println(concatZYX1("a")) // azyx
    println(concatZYX2("a")) // azyx

    /**
     * Partially applied functions
     */
    val add5First = sum1(5, _: Int)
    val add5Second = sum2(5) _

    println(add5First(10)) // 15
    println(add5Second(10)) // 15

    /** ***********************************************************************/

    /**
     * Memoization
     */
    println(memoizeLookup(42))
    println(memoizeLookup(42))

    val memoizedLookup1 = memoizeLookup
    val memoizedLookup2 = memoizeLookup

    println(memoizedLookup1(1))
    println(memoizedLookup1(1))
    println(memoizedLookup1(1))

    println(memoizedLookup2(1))
    println(memoizedLookup2(1))
    println(memoizedLookup2(1))
  }

  /** ***********************************************************************/

  /**
   * Focused mutability
   */
  val fiveNumbers = infiniteNumbers take 5 force

  for (number <- fiveNumbers) println(number)

}
