package lectures.part2fp

object HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // function that applies a function n times over a value x.
  // nTimes(f, n, x)
  // nTime(f, 3, x) = f(f(f(x)))
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))
  }

  println(nTimesBetter(plusOne, 10)(1))

  def curriedFormatter2(c: String)(x: Double): String = c.format(x)
  val curriedFormatter = (c: String) => (x: Double) => c.format(x)

  def mult0(x: Int) = x * 2
  val mult1 = (x: Int) => x * 2

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  println(standardFormat(123.2))

  def toCurry[A, B, T](f: (A, B) => T): (A => B => T) = x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = (x, y) => f(x)(y)

  def compose[A, B, C](f: B => C, g: A => B): A => C = (x) => f(g(x))

  def superAdder: (Int => Int => Int) = toCurry(_ + _)

  def add4 = superAdder(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3
  val composed = compose(add2, times3)
  println(composed(20))

  val stringToInt1 = (str: String) => {
    str.toInt
  }
  val stringToInt2 = { str: String =>
    str.toInt
  }
  println(stringToInt1("12"))
  println(stringToInt2("12"))

  val niceInc: Int => Int = _ + 1
  val niceInc1: Int => Int = (x) => x + 1

}
