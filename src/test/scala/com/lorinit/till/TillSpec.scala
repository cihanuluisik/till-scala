package com.lorinit.till

import com.lorinit.till.Item.{Apple, Orange}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class TillSpec extends FlatSpec with Matchers with BeforeAndAfter  {

  var till: Till = _

  before {
    till = new Till()
  }

  "Till" should " calculate to 0 given nothing" in {
    val total = till.calculate(Nil)
    total should be(0)
  }

  "Till" should " calculate to 0 given empty list" in {
    val total = till.calculate(List())
    total should be(0)
  }

  "Till" should " calculate to 60 given a list with one orange" in {
    val total = till.calculate(List(Orange))
    total should be(25)
  }

  "Till" should " calculate to 25 given a list with one apple" in {
    val total = till.calculate(List(Apple))
    total should be(60)
  }

  "Till" should " calculate to 50 given a list with two apples" in {
    val total = till.calculate(List(Apple, Apple))
    total should be(120)
  }

  "Till" should " calculate to 170 given a list with two apples and two oranges" in {
    val total = till.calculate(List(Apple, Apple, Orange, Orange))
    total should be(170)
  }

  // so many repetitive steps.. carrying on with PropSpec from here on ..

}
