package lectures.part2fp

import scala.util.Random

object Sequences extends App {
  // sequences
  val aSequence = Seq(2, 1, 3, 4)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // ranges
  val aRange: Seq[Int] = 1 to 4
  aRange.foreach(println)

  // lists
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(numbers)
  println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0
  println(numbers.mkString(" "))

  // arrays <> seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  val double = (x: Int) => x * 2

  // vectors vs list
  val maxRuns = 1000
  val maxCapacity = 1000000;
  val getWriteTime: Seq[Int] => Double = collection => {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns / Math.pow(10, 6) // milliseconds
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating el in the middle takes long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-el chunk
  println(getWriteTime(numbersVector))
}
