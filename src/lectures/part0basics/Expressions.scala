package lectures.part0basics

object Expressions extends App {
  // + - *  & | ^ << >> >>>
  val x = 1 + 2
  println(x)

  print(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVar = 2;
  aVar += 3
  // -= *= /=
  println(aVar)

  // Instructions (DO) vs Expressions (VALUE)

  // if expressions
  val aCond = true
  val aCondVal = if (aCond) 5 else 3
  print(aCondVal)

  // NEVER DO THIS.  Functional!
  var i = 0
  while(i < 4) {
    println(i)
    i += 1
  }

  val aWeird = (aVar  = 2)
  println(aWeird) // This returns () .  Unit == void : a side effect.

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "asd"
  }
  println(aCodeBlock)
}
