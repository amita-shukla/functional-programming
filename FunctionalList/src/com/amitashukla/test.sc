abstract class MyList[+A] {
  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean
  def add[B>:A](element: B) : MyList[B] = new MyNonEmptyList[B](element,this)
}

object MyEmptyList extends MyList[Nothing]{
  override def head: Nothing = throw new NoSuchElementException("Head of an Empty list : ")

  override def tail: MyList[Nothing] = throw  new NoSuchElementException("Tail of an Empty list!" )

  override def isEmpty: Boolean = true

  override def toString : String = ""
}


case class MyNonEmptyList[A] (head: A, tail:MyList[A]) extends MyList[A]{
  override def isEmpty: Boolean = false
  override def toString : String = head + ", " + tail.toString
}


val list = MyNonEmptyList(1,MyEmptyList)
val b = list.head
list.tail
list.add(2)

/**
  * Checking the behaviour of add (B:>A)
  */
abstract class Animal
class Dog extends Animal
class Cat extends Animal

val dogs = MyNonEmptyList(new Dog(), MyEmptyList)
val animals = dogs.add(new Cat())

