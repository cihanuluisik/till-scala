package com.lorinit.till

import com.lorinit.till.Item.{Apple, Kiwi, Orange}
import org.scalatest._
import org.scalatest.prop._

class TillPropSpec extends PropSpec with TableDrivenPropertyChecks with Matchers with BeforeAndAfter {

  var till: Till = _

  before {
    till = new Till(
    {
      case Apple  => 60
      case Orange => 25
      case _      => 0    // default.. to be agreed with customer!
    },
      (item: Item) => Offer(1000000,0) // default offer.. to be agreed
    )

  }

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
