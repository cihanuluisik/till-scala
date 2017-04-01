package com.lorinit.till

import com.lorinit.till.Item.{Apple, Kiwi, Orange}
import org.scalatest._
import org.scalatest.prop._

class TillPropSpec extends PropSpec with TableDrivenPropertyChecks with Matchers with BeforeAndAfter {

  var till  = Till withPriceList (Apple -> 60, Orange -> 25)

  val testCases = Table( ("list",                                "total"),
    (Nil,                                   0),
    (List(),                                0),
    (List(Kiwi),                            0),  // agree on default price or error ?
    (List(Orange),                          25),
    (List(Apple),                           60),
    (List(Apple, Apple),                    120),
    (List(Apple, Orange, Apple),            145),
    (List(Apple, Apple, Orange, Apple),     205))

  property("Calculating total price for shopping list") {
    forAll(testCases) { (shoppingList, total) => till.calculate(shoppingList) should be(total) }
  }

}
