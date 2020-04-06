package lectures.part1oop

object OOBasics extends App {
  val person = new Person("CJ", 27)
  println(person.age)
  person.greet("KT")
  person.greet()

  val author = new Author("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))

  val counter = new Counter
  counter.inc(2).print
}

// Class params are not fields
class Person(name: String, val age: Int) {
  def greet(name: String): Unit = {
    println(s"${this.name} says: Hi, $name")
  }
  def greet(): Unit = {
    greet("")
  }

  // multiple constructors (rather use optional parameters)
  def this(name: String) = this(name, 0)
}

class Author(forename: String, surname: String, val year: Int) {
  def fullName: String = s"$forename $surname"
}

class Novel(name: String, year: Int, author: Author) {
  def authorAge = year - author.year
  def isWrittenBy(author: Author) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {
  def inc = {
    println("inc")
    new Counter(count + 1)
  } // immutability
  def dec = {
    println("dec")
    new Counter(count - 1)
  }
  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}
