package lectures.part0basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 23))

  def bFunction(): Int = 42
  println(bFunction())
  println(bFunction)

  def aRepeat(a: String, n: Int): String = {
    if (n <= 1) a
    else a + "," + aRepeat(a, n - 1)
  }

  println(aRepeat("hello", 4))

  def aSideEffectFunc(a: String): Unit = {
    println("hello" + a)
  }

  aSideEffectFunc("CJ")

  def aCool(n: Int): Int = {
    def inner(a: Int, b: Int): Int = a + b
    inner(n, n-1)
  }
  println(aCool(4))

  def getFunc(n: Int): Int => Int = {
    def sum(x: Int) = {
      x + n
    }
    sum
  }

  println(getFunc(2)(3))
}
