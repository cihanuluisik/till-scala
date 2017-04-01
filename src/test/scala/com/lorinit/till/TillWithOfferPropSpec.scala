package com.lorinit.till

import com.lorinit.till.Item.{Apple, Kiwi, Orange}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{BeforeAndAfter, Matchers, PropSpec}

class TillWithOfferPropSpec extends PropSpec with TableDrivenPropertyChecks with Matchers with BeforeAndAfter {

  var tillWithOffer = Till withPrices  (Apple -> 60, Orange -> 25)   withOffers  ( Apple -> Offer(2, 60),  Orange -> Offer(3, 25))

  val testCases = Table( ("list",                                                    "total"),
                          (Nil,                                                       0),
                          (List(),                                                    0),
                          (List(Kiwi),                                                0), // an item without any price listed
                          (List(Orange),                                              25),
                          (List(Apple),                                               60),
                          (List(Orange, Orange),                                      50), // under discount threshold up to here
                          (List(Apple, Apple),                                        60), // first discounted list
                          (List(Orange, Orange, Orange),                              50),
                          (List(Orange, Orange, Orange, Apple, Apple, Apple),         170),
                          (List(Orange, Orange, Apple, Orange, Apple, Apple),         170) ) // unordered list

  property("Calculating total price for shopping list with possible offers") {
    forAll(testCases) { (list, total) => tillWithOffer.calculate(list) should be(total) }
  }

}
