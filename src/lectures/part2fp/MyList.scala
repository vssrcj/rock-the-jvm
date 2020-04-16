package lectures.part2fp

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](el: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](el: B): MyList[B] = new Cons(el, Empty)
  override def printElements: String = ""
  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = {}
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    Empty
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](el: B): MyList[B] = new Cons(el, this)
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons (h, t ++ list)
  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  override def foreach(f: A => Unit): Unit = {
    if (!t.isEmpty) {
      f(h)
      t.foreach(f)
    }
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons (sortedList.head, insert(x, sortedList.tail))
    }
    val sorted = t.sort(compare)
    insert(h, sorted)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val list2 = new Cons(4, new Cons(5, new Cons(6, Empty)))
  println(list.tail.head)
  println(list.toString)

  val listOfIntegers: MyList[Int] = Empty;
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Bai", Empty))
  println(listOfStrings.toString)

  println(list.map(el => el * 2))
  println(list.map(_ * 2))
  println(list.map((el: Int) => el * 2))

  println(list.filter(el => el % 2 == 0));

  println((list ++ list2).toString)

  println(list.map(new Function1[Int, MyList[Int]] {
    override def apply(el: Int): MyList[Int] = new Cons(el, new Cons(el + 1, Empty))
  }))
  println(list.map((el: Int) => new Cons(el, new Cons(el + 1, Empty))))
  println(list.flatMap((el: Int) => new Cons(el, new Cons(el + 1, Empty))))

  list.foreach(println)
  println(list.sort((a, b) => b - a))
  println(list.zipWith(list2, (a: Int, b: Int) => a + b))
  println(list.zipWith(list2, (_: Int) + "-" + (_: Int)))

  println(list.fold(0)(_ + _))

  // for comprehensions.
  val combinations = for {
    n <- list
    string <- listOfStrings
  } yield n + "-" + string
  println(combinations)
}