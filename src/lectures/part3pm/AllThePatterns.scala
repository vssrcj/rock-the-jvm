package lectures.part3pm

import lectures.part2fp.{Cons, Empty, MyList}


object AllThePatterns extends App {
  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }
//
//  // 2 - match anything
//  // 2.1
//  val matchAnything = x match {
//    case _ =>
//  }
//  println(matchAnything)
//
//  // 2.2 variable
//  val matchAVar = x match {
//    case something => s"I've found $something"
//  }
//
//  // 3 - tuples
//  val aTuple = (1, 2)
//  val matchATuple = aTuple match {
//    case (1, 1) =>
//    case (something, 2) => s"I've found $something"
//  }
//  println(matchATuple)
//
//  val nestedTuple = (1, (2, 3))
//  val matchANestedTuple = nestedTuple match {
//    case (_, (2, v)) =>
//  }
//  // PMs can be nested!
//
//  // 4 - case classes - constructor pattern
//  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
//  val matchAList = aList match {
//    case Empty =>
//    case Cons(head, Cons(subhead, subTail)) =>
//  }
//
//  // 5 - list patterns
//  val aStandardList = List(1, 2, 3, 42)
//  val standardListMatching = aStandardList match {
//    case List(1, _, _, _) => // extractor
//    case List(1, _*) => // list of arbitrary length
//    case 1 :: List(_) => // infix pattern
//    case List(1, 2, 3) :+ 42 => // infix pattern
//  }
//
//  // 6 - type specifiers
//  val unknown: Any = 2
//  val unknownMatch = unknown match {
//    case list: List[Int] =>
//    case _ =>
//  }
//
//  // 7 - name binding
//  val nameBindingMatch = aList match {
//    case notEmptyList @ Cons(_, _) => // name binding => us the name later (here)
//    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
//  }
//
//  // 8 - multi-pattern
//  val multipattern = aList match {
//    case Empty | Cons(0, _) => // compound pattern
//  }
//
//  // 9 - if guards
//  val secondElementSpecial = aList match {
//    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
//  }

  // All.

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }
  println(numbersMatch)
  // JVM trick question
  // JVM erase generic types after touching.  i.e.  case listOfStrings: List[String] = case listOfStrings: List
  // type eraser
}
