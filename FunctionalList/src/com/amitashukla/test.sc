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

  /**
    * O(n) : n is number of elements
    * @return
    */
  def last : A = this match {
    case MyEmptyList => throw new Error("Last of an empty list")
    case MyNonEmptyList(head,MyEmptyList) => head
    case MyNonEmptyList(head,tail) => tail.last
  }

  def init[B>:A] : MyList[B] = this match {
    case MyEmptyList => throw new Error("Init of an empty list")
    case MyNonEmptyList(head,MyEmptyList) => MyEmptyList
    case MyNonEmptyList(head,tail) => MyNonEmptyList(head,tail.init)
  }

  def insert[B>:A](element: B, list: MyList[B])(implicit ordering : Ordering[B]): MyList[B] = list match {
    case MyEmptyList => MyNonEmptyList(element,MyEmptyList)
    case MyNonEmptyList(head,tail) =>
      if(ordering.lt(element, head))
        list.add(element)
      else
        insert(element,tail)(ordering).add(head)
  }

  def iSort[B>:A](implicit ordering: Ordering[B]) : MyList[B] = this match {
    case MyEmptyList => MyEmptyList
    case MyNonEmptyList(head,tail) =>  insert(head,tail.iSort(ordering))(ordering)
  }
}


//checking initialization
val list = MyNonEmptyList(1,MyEmptyList)
//checking head
val b = list.head
//checking tail
list.tail
//checking add
val c = list.add(2)
//checking last
val last = c.last
//c.tail.tail.last   //this throws an error
//checking init
val init = c.init
c.tail.init
//c.tail.tail.init //this throws an error
//checking sorting
val sorted = c.iSort
val lastOfSorted = sorted.last
val initOfSorted = sorted.init
//checking pattern matching...
val matched = list match {
  case MyNonEmptyList(1,MyEmptyList) => "wow"
}
// Checking the behaviour of add (B:>A)
abstract class Animal
class Dog extends Animal
class Cat extends Animal
val dogs = MyNonEmptyList(new Dog(), MyEmptyList)
val animals = dogs.add(new Cat())
//checking to String
animals.tail.toString
//checking length
animals.length

