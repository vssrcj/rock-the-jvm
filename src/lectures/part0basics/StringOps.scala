package lectures.part0basics

object StringOps extends App {
  val str: String = "Hello, I am learning Scala"

  println(str.charAt(1))
  println(str.substring(2, 8))
  val t = str.split(" ")
  println(t.toList)
  val q = "A" +: "B"
  val r = "A" :+ "B"
  println(q)
  println(r)
  println("a" :+ 45)

  // S-interpolators
  val name = "CJ"
  val age = 12
  val greeting = s"Hello, my name is $name, and I am $age $$sdf"
  println(greeting)

  // F-interpolator
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"

  // raw-interpolator
  println(raw"This is a \n newline")
  println("This is a \n newline")

  println(myth)
}
