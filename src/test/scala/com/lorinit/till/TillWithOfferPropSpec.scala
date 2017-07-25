package com.lorinit.till

import com.lorinit.till.Item.{Apple, Kiwi, Orange, Banana}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfter, Matchers, PropSpec}

class TillWithOfferPropSpec extends PropSpec with TableDrivenPropertyChecks with Matchers with BeforeAndAfter {

  var tillWithOffer = Till withPrices  (Apple -> 60, Orange -> 25, Banana -> 20)  withOffers(OfferCheapestFree(List(Apple, Banana), 2, 1), OfferBuyNGetYFree(Orange, 3, 1) )

  val testCases = Table( ("list",                                                    "total"),
                          (Nil,                                                       0),
                          (List(),                                                    0),
                          (List(Kiwi),                                                0),
                          (List(Orange),                                              25),
                          (List(Apple),                                               60),
                          (List(Banana),                                              20),
                          (List(Orange, Orange),                                      50), // under discount threshold up to here
                          (List(Apple, Apple),                                        60), // first discounted list
                          (List(Apple, Banana),                                       60), // cheapest is banana
                          (List(Apple, Apple, Banana, Banana),                        120),
                          (List(Apple, Apple,Apple, Banana),                          120),
                          (List(Apple, Apple, Apple, Apple, Banana, Banana),          180),
                          (List(Orange, Orange, Orange),                              50),
                          (List(Orange, Orange, Orange, Apple, Apple, Apple),         170),
                          (List(Orange, Orange, Apple, Orange, Apple, Apple),         170) ) // unordered list

  property("Calculating total price for shopping list with possible offers") {
    forAll(testCases) { (list, total) =>
      tillWithOffer.calculate(list) should be(total)
    }
  }

}
