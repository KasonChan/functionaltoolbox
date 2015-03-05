package io

/**
 * Created by kasonchan on 3/5/15.
 */
trait IO {
  def echo(args: Any*) = {
    for (arg <- args)
      println(arg)
  }
}
