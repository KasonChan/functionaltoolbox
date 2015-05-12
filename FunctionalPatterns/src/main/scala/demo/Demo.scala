package demo

import filtermapreduce.FilterMapReduce

/**
 * Created by kasonchan on 5/11/15.
 */
object Demo extends FilterMapReduce {

  def main(args: Array[String]) {
    val prices = Vector(5.0, 30.0, 20.5, 3.5, 1.5, 50.0)

    val totalDiscount1 = calculateTotalDiscount(prices)
    println(totalDiscount1)

    val totalDiscount2 = calculateTotalDiscountNamed(prices)
    println(totalDiscount2)

  }

}
