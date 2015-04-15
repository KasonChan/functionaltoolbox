package io

/**
 * Created by kasonchan on 4/13/15.
 */
trait IO {
  def echo(any: Any*) = any.map(x => println("io: " + x))
}
