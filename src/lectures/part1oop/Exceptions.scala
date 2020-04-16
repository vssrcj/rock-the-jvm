package lectures.part1oop

object Exceptions extends App {
  val x: String = null
//  println(x.length)
//  val weird = throw new NullPointerException

  def getInt(withEx: Boolean): Int = {
    if (withEx) throw new RuntimeException("No int for you!")
    else 42
  }

  val p = try {
    getInt(true)
  } catch {
    case e: RuntimeException => {
      println("Caught runtime exception")
      43
    }
  } finally  {
    // use finally only for side effects.
    println("Finally")
  }
  println(p)

  // define own exceptions
  class MyException extends Exception
  val exception = new MyException

//  val array = Array.ofDim(Int.MaxValue)

  def infinite: Int = 1 + infinite
//  val noLimit =

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
//    def multiply(x: Int, y: Int) = {
//      val result = x - y
//      if (x > 0 && y > 0 && result < 0) throw new OverflowException
//      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
//      else result
//    }
  }
//  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.add(2, 10))
}
