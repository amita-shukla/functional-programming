package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c > r) return 0
    if (c == 0) return 1
    pascal(c, r - 1) + pascal(c - 1, r - 1)

  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {

    //      List[Character] parens = chars.filter{  c : Character =>
    //        (c  == '(') || ( c == ')')
    //      }

    def balanced(parens: List[Char], open: Integer, closed: Integer): Boolean = {
      if (parens.isEmpty) {
        return open == closed
      }
      if (closed > open)
        return false
      val c = parens.head
      if (c == '(') {
        return balanced(parens.tail, open + 1, closed)
      } else if (c == ')') {
        return balanced(parens.tail, open, closed + 1)
      } else {
        return balanced(parens.tail, open, closed)
      }

      true
    }

    balanced(chars, 0, 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty && money > 0) return 0
    if (money == 0) return 1
    if (money < 0) return 0
    countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
