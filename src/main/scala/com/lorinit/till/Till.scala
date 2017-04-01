package com.lorinit.till

import scala.annotation.tailrec
import scala.collection.mutable


case class OfferList(offersList:(Item,Offer)*){
  val offer:Map[Item, Offer] = offersList.toMap withDefaultValue Offer(1000000,0)
}


case class PriceList(priceList:(Item,Int)*){
  val price:Map[Item, Int] = priceList.toMap withDefaultValue 0
}


class Till(priceList: PriceList, offerList: OfferList) {

  def this(priceList: PriceList) = this(priceList, new OfferList())

  def calculate(shoppingList: List[Item]) :Int = calculate(mutable.Map(), shoppingList)

  @tailrec
  private def calculate(acc: mutable.Map[Item, Int], list: List[Item]): Int = list match {
    case Nil if acc.isEmpty => 0
    case Nil => (for ((item, count) <- acc ) yield priceList.price(item) * count - discountTotal(item, count)).sum
    case item :: tail =>  acc.put(item, acc.getOrElseUpdate(item, 0) + 1); calculate(acc, tail)
  }


  private def discountTotal(item: Item, count: Int): Int = {
    count / offerList.offer(item).threshold * offerList.offer(item).discountAmount
  }

}
