package com.amitashukla

/**
  * Created by amita on 6/6/17.
  * Every case class parameter is implicitly also a field of the class (it's like the parameter declaration was prefixed with val)
  */

case class MyNonEmptyList[A] (head: A, tail:MyList[A]) extends MyList[A]{
  override def isEmpty: Boolean = false
  override def toString : String = head + ", " + tail.toString
}
