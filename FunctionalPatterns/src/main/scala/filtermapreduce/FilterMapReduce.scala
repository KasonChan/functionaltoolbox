package filtermapreduce

/**
 * Created by kasonchan on 5/11/15.
 */
trait FilterMapReduce {

  def calculateTotalDiscount(prices: Seq[Double]): Double = {
    // Select the prices that are greater than 20.0
    prices filter (price => price >= 20.0) map
      // Map the selected prices to get 10% of them
      (price => price * 0.10) reduce
      // Sum the prices together
      ((total, price) => total + price)
  }

  def calculateTotalDiscountNamed(prices: Seq[Double]): Double = {
    // Select the prices that are greater than 20.0
    def isGreaterThan20(price: Double) = price >= 20.0
    // Get 10% of the selected price
    def tenPercent(price: Double) = price * 0.10
    // Sum the prices together
    def sumPrices(total: Double, price: Double) = total + price

    prices filter isGreaterThan20 map tenPercent reduce sumPrices
  }

}
