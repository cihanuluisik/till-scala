package com.lorinit.till

import com.lorinit.till.Item.{Apple, Orange}
import org.scalatest._
import prop._

class TillPropSpec extends PropSpec with TableDrivenPropertyChecks with Matchers with BeforeAndAfter  {

  var till: Till = _

  before {
    till = new Till()
  }

  val testCases = Table( ("list",                                "total"),
                          (Nil,                                   0),
                          (List(),                                0),
                          (List(Orange),                          25),
                          (List(Apple),                           60),
                          (List(Apple, Apple),                    120),
                          (List(Apple, Orange, Apple),            145),
                          (List(Apple, Apple, Orange, Apple),     205))

  property("Calculating total price for shopping list") {
    forAll(testCases)   { (shoppingList, total) => till.calculate(shoppingList) should be (total) }
  }

}
