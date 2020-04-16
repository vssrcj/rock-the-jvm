package lectures.part1oop

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](el: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](el: B): MyList[B] = new Cons(el, Empty)
  override def printElements: String = ""
  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
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
  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons (h, t ++ list)
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }
}

trait MyPredicate[-T] {
  def test(el: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(el: A): B
}



object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val list2 = new Cons(4, new Cons(5, new Cons(6, Empty)))
  println(list.tail.head)
  println(list.toString)

  val listOfIntegers: MyList[Int] = Empty;
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Bai", Empty))
  println(listOfStrings.toString)

  println(list.map(new MyTransformer[Int, Int] {
    override def transform(el: Int): Int = el * 2
  }))
  println(list.map((el: Int) => el * 2))

  println(list.filter(new MyPredicate[Int] {
    override def test(el: Int): Boolean = el % 2 == 0
  }))

  println((list ++ list2).toString)

  println(list.map(new MyTransformer[Int, MyList[Int]] {
    override def transform(el: Int): MyList[Int] = new Cons(el, new Cons(el + 1, Empty))
  }))
  println(list.map((el: Int) => new Cons(el, new Cons(el + 1, Empty))))
//  println(list.flatMap((el: Int) => new Cons(el, new Cons(el + 1, Empty))))
}