package demo

import chainofoperations.ChainOfOperations
import filtermapreduce.FilterMapReduce

/**
 * Created by kasonchan on 5/11/15.
 */
object Demo extends FilterMapReduce with ChainOfOperations {

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

  }

}
