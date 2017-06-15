/** Notes :
  * 1. It is necessary to define the classes/objects before the list to avoid "wrong forward reference" error
  * 2. Using a lot of pattern matching in the subsequent function
  * Remember : The compiler generates a lot of code under the hood if pattern matching is used for booleans.
  * For booleans, plane old if-else is still more efficient.
  * 3. In functions such as 'add' or 'concat, element/list os prepended intead of appended.
  * This is being done on purpose, as they imitate the functions '::' and ':::' respectively.
  * These functions are right associative, and hence prepending makes more sense
  * e.g. 1 :: List(2,3,4) = List(1,2,3,4) instead of List(2,3,4,1)
  */

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

  /**
    A list with all elements except the last one
    */
  def init[B>:A] : MyList[B] = this match {
    case MyEmptyList => throw new Error("Init of an empty list")
    case MyNonEmptyList(head,MyEmptyList) => MyEmptyList
    case MyNonEmptyList(head,tail) => MyNonEmptyList(head,tail.init)
  }

  /**
    * Append the elements of given list before this list
    */

  /*def concat[B>:A](that : MyList[B]) : MyList[B] = this match {
    case MyEmptyList => that
    case MyNonEmptyList(head,tail) => tail.concat(that).add(head)
  }*/

  def concat[B>:A](that : MyList[B]) : MyList[B] = {
    if(that.isEmpty) this
    else /*tail.concat(that).add(head)*/ this.concat(that.tail).add(that.head)
  }

  def map[B>:A](f : A => B) : MyList[B] = if (isEmpty) this else tail.map(f).add(f(head))

  def foldLeft[B](start : B)(f : (B,A)=> B) : B = {
    if (isEmpty){
      start
    } else{
      val current = f(start,this.head)
      this.tail.foldLeft(current)(f)
    }
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
//checking concat
val d = MyNonEmptyList(3,MyEmptyList)
val e = d.add(4)
val concatenated = c.concat(e)
//checking map
val f = concatenated.map(x => x+1)
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


