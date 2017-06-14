//it is necessary to define the classes/objects before the list to avoid "wrong forward reference" error

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
 
abstract class MyList[+A] {
  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean
  def add[B>:A](element: B) : MyList[B] = new MyNonEmptyList[B](element,this)
  def length : Int = {
    if(isEmpty) 0 else 1 + tail.length
  }

  def insert[B>:A](element: B, list: MyList[B]): MyList[B] = list match {
    case MyEmptyList => MyNonEmptyList(element,MyEmptyList)
    case MyNonEmptyList(head,tail) =>  if(element <= head) list.add(element) else insert(element,tail).add(head)
  }

  def iSort[B>:A](xs : MyList[B]) : MyList[B] = xs match {
    case MyEmptyList => MyEmptyList
    case MyNonEmptyList(head,tail) =>  insert(head,iSort(tail))
  }
}



val list = MyNonEmptyList(1,MyEmptyList)
val b = list.head
list.tail
list.add(2)
list match {
  case MyNonEmptyList(1,MyEmptyList) => "wow"
}
/**
  * Checking the behaviour of add (B:>A)
  */
abstract class Animal
class Dog extends Animal
class Cat extends Animal

val dogs = MyNonEmptyList(new Dog(), MyEmptyList)
val animals = dogs.add(new Cat())
animals.tail.toString
animals.length

