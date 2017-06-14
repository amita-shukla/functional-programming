import com.amitashukla.{MyEmptyList, MyNonEmptyList}

/*case class MyNonEmptyList[A] (head: A, tail:amitashukla.MyList[A]) extends amitashukla.MyList[A]{
  override def isEmpty: Boolean = false
  override def toString : String = head + ", " + tail.toString
}

abstract class MyList[+A] {
  def head : A
  def tail : amitashukla.MyList[A]
  def isEmpty : Boolean
  def add[B>:A](element: B) : amitashukla.MyList[B] = new amitashukla.MyNonEmptyList[B](element,this)
}

object MyEmptyList extends amitashukla.MyList[Nothing]{
  override def head: Nothing = throw new NoSuchElementException("Head of an Empty list : ")

  override def tail: amitashukla.MyList[Nothing] = throw  new NoSuchElementException("Tail of an Empty list!" )

  override def isEmpty: Boolean = true

  override def toString : String = ""
}*/


val list = MyNonEmptyList(1,MyEmptyList)
val b = list.head
list.tail
list.add(2)
