package lectures.part2fp

object WhatsAFunction extends App {
  val doubler = new MyFunction[Int, Int] {
    override def apply(el: Int): Int = el * 2
  }

  println(doubler(2))

  val adder: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  val adder2: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  val adder3: ((Int, Int) => Int) = (v1: Int, v2: Int) => v1 + v2
  println(adder(2, 3))

  val superAdded: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  val superAdder2 = (x: Int) => (y: Int) => x + y


//  val superAdder((x: Int) => (y: Int) => x + y)

  var adder4 = superAdded(4)
  println(adder4(3))
}

trait MyFunction[A, B] {
  def apply(el: A): B
}