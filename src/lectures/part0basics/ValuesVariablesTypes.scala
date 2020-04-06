package lectures.parto0basics

object ValuesVariablesTypes extends App {
  // VAL IS IMMUTABLE
  val x = 42
  println(x)

  val aString: String = "Hello"

  val aBoolean: Boolean = false
  val aShort: Short = 123
  val aChar: Char = '1'
  val aLong: Long = 123123112123123L
  val aFloat: Float = 23.123f
  val aDouble: Double = 123.123

  // VARIABLES ARE MUTABLE / SIDE EFFECTS
  var aVar = 123
  aVar = 42
}
