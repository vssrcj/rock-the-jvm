package lectures.part1oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.sql.{Date => SqlDate}
//import playground.Cinderella

object PackagingAndImports extends App {
  //  val cindy = new Cinderella
  val cindy = new Princess
  val charming = new PrinceCharming

  println(SPEED_OF_LIGHT)

  // default imports
  // java.lang = String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
