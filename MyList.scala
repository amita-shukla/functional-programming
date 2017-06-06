/**
  * Created by amita on 6/6/17.
  */
abstract class MyList[A] {
  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean
  def add(element: A) : MyList[A] = new NonEmptyList[A](element,this)




}

class MyEmptyList[A] extends MyList[A]{
  override def head: A = throw new NoSuchElementException("Head of an Empty list : " + this.toString)

  override def tail: MyList[A] = ???

  override def isEmpty: Boolean = true

  override def toString : String = ""
}

class NonEmptyList[A] (val head: A, val tail:MyList[A]) extends MyList[A]{
  override def isEmpty: Boolean = false
  override def toString : String = head + ", " + tail.toString
}

object MyList {
  def apply[A](xs : A*): MyList[A] = new MyList()
}
