package com.lorinit.till

import scala.annotation.tailrec
import scala.collection.mutable

class Till(price: Item => Int, offer: Item => Offer) {

  def calculate(shoppingList: List[Item]) :Int = calculate(mutable.Map(), shoppingList)

  @tailrec
  private def calculate(acc: mutable.Map[Item, Int], list: List[Item]): Int = list match {
    case Nil if acc.isEmpty => 0
    case Nil => (for ((item, count) <- acc ) yield price(item) * count - discountTotal(item, count)).sum
    case item :: tail =>  acc.put(item, acc.getOrElseUpdate(item, 0) + 1); calculate(acc, tail)
  }

  private def discountTotal(item: Item, count: Int): Int = {
    count / offer(item).threshold * offer(item).discountAmount
  }

}
