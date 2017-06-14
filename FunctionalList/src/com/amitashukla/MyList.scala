package com.amitashukla

/**
  * Created by amita on 13/6/17.
  */
abstract class MyList[+A] {
  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean

  /**
    * Equivalent to the construction operation :: (cons)
    * Appends an element in front of the list
    */
  def add[B>:A](element: B) : MyList[B] = new MyNonEmptyList[B](element,this)
}
