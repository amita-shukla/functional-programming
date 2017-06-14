package com.amitashukla

/**
  * Created by amita on 13/6/17.
  */
object MyEmptyList extends MyList[Nothing]{
  override def head: Nothing = throw new NoSuchElementException("Head of an Empty list : ")

  override def tail: MyList[Nothing] = throw  new NoSuchElementException("Tail of an Empty list!" )

  override def isEmpty: Boolean = true

  override def toString : String = ""
}
