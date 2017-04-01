package com.lorinit.till

import scala.annotation.tailrec
import scala.collection.mutable

class Till(prices: PriceList) {

  var offers: OfferList = OfferList()

  def calculate(shoppingList: List[Item]) :Int = calculate(mutable.Map(), shoppingList)

  @tailrec
  private def calculate(acc: mutable.Map[Item, Int], list: List[Item]): Int = list match {
    case Nil if acc.isEmpty => 0
    case item :: tail =>  acc.put(item, acc.getOrElseUpdate(item, 0) + 1); calculate(acc, tail)
    case Nil          => (for ((item, count) <- acc ) yield priceTotal(item, count) - discountTotal(item, count)).sum
  }

  private def priceTotal(item: Item, count: Int)     =  prices.price(item) * count
  private def discountTotal(item: Item, count: Int)  =  count / offers.offer(item).threshold * offers.offer(item).discountAmount

  def withOffers(offerTuples:(Item,Offer)*)       =  {offers = OfferList(offerTuples:_*); this}

}


object Till {
  def withPrices(priceTuples: (Item,Int)* )        =  new Till(new PriceList(priceTuples:_*))
}
