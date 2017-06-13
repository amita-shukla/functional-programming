import scala.runtime.Nothing$

/**
  * Created by amita on 6/6/17.
  */

case class MyNonEmptyList[A] (head: A, tail:MyList[A]) extends MyList[A]{
  override def isEmpty: Boolean = false
  override def toString : String = head + ", " + tail.toString
}

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



/*
object MyList {
  //def apply[A](xs : A*): MyList[A] = new MyList()
  def add(element: A) : MyList[A] = new MyNonEmptyList[A](element,this)
}*/

/*
object MyListApp extends App{

  val list : MyNonEmptyList[Int] = new MyNonEmptyList(1, MyEmptyList)
  println(list.toString)

}*/
