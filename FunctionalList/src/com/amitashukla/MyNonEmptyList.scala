package com.amitashukla

/**
  * Created by amita on 6/6/17.
  */

case class MyNonEmptyList[A] (head: A, tail:MyList[A]) extends MyList[A]{
  override def isEmpty: Boolean = false
  override def toString : String = head + ", " + tail.toString
}
