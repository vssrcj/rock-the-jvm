package lectures.part0basics

object CBNvsCBV extends App {
  def byValue(x: Long): Unit = {
    println("by val: " + x)
    println("by val: " + x)
  }
  def byName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  byValue(System.nanoTime())
  byName(System.nanoTime())

  def inf(): Int = 1 + inf()

  def printFirst(x: Int, y: => Int) = println(x)

//  printFirst(inf(), 34)
  printFirst(34, inf())
}
