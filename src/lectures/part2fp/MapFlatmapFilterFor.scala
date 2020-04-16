package lectures.part2fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println(list.filter(_ % 2 == 0))

  println(list.flatMap(x => List(x, x + 1)))

  val numbers = List(1, 2, 3)
  val chars = List("a", "b", "c", "d")
  println(numbers.flatMap(n => chars.map(n.toString + '-' + _)))

  val forCombinations = for {
    n <- numbers if n > 0
    c <- chars
  } yield n.toString + '-' + c

  println(forCombinations)
}
