package lectures.part2fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  val aTuple = Tuple2(2, "hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "Goodbye"))
  println(aTuple.swap)

  // maps
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 555), ("Daniel" -> 789))
  // a -> b is sugar for (a, b)
  println(phoneBook)

  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))

  val newPairing = "Mary" -> 678
  val newPairing2 = ("John", 123)
  val newPhoneBook = phoneBook + newPairing + newPairing2
  println(newPhoneBook)

  println(newPhoneBook.map(pair => pair._1))
  println(newPhoneBook.map(pair => pair._1.toLowerCase() -> pair._2))
  println(Map(("CJ", 12), ("cj", 13)).map(pair => pair._1.toLowerCase() -> pair._2))

  println(newPhoneBook.view.filterKeys(key => key.startsWith("J")).toList)
  println(newPhoneBook.filter(pair => pair._1.startsWith("J")))

  println(List(("CJ", 23)).toMap)

  val names = List("CJ", "Charles", "Donkey", "James")
  println(names.groupBy(name => name.charAt(0)))

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
     if (friends.isEmpty) networkAcc
     else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(add(empty, "Mary"), "Bob"), "Jonty")
  println(network)

  val network2 = friend(network, "Bob", "Mary")
  println(network2)
  println(unfriend(network2, "Bob", "Jonty"))
  println(unfriend(network2, "Bob", "Mary"))
  println(remove(network2, "Bob"))


  val people = add(add(add(empty, "Mary"), "Bob"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(_._2.size)._1
  }

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
//    network.filter(k => network(k._1).isEmpty).size
    network.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(people))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
}