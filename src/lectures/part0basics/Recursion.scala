package lectures.part0basics

import scala.annotation.tailrec

object Recursion extends App {
  //  @tailrec
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("Compute start " + n)
      val result = n * factorial(n-1)
      println("Compute result " + n)
      result
    }
  }

  @tailrec
  def factorialGood(n: Int, acc: BigInt = 1): BigInt = {
    if (n <= 1) acc;
    else factorialGood(n - 1, n * acc) // tail recursion
  }

  println(factorial(5))
  println(factorialGood(5000))

  def fib(n: Int, a: Int = 0, b: Int = 1): BigInt = {
    if (n <= 1) a + b
    else fib(n - 1, b, a + b)
  }

  // fib(0) - 1
  // fib(1) - 1
  // fib(2) - acc: 1: acc + acc: 2
  // fib(3) - acc: 2:
  println(fib(1))
  println(fib(2))
  println(fib(3))
  println(fib(4))
  println(fib(5))
  println(fib(6))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeRec(t: Int): Boolean = {
      if (t <= 3) true
      else if (n % t == 0) false
      else isPrimeRec(t - 1)
    }
    isPrimeRec(n / 2)
  }
  println(isPrime(100))
  println(isPrime(2003))
}
