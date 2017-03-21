package com.lorinit.till

import scala.annotation.tailrec
import scala.collection.mutable

class Till(priceList: Item => Int, offers: Item => Offer) {

  def calculate(shoppingList: List[Item]) :Int = calculate(mutable.Map(), shoppingList)

  @tailrec
  private def calculate(acc: mutable.Map[Item, Int], list: List[Item]): Int = list match {
    case Nil if acc.isEmpty => 0
    case Nil => acc.foldLeft(0)((a, b) => a + priceList(b._1) * b._2 - (b._2 / offers(b._1).threshold) * offers(b._1).discountAmount)
    case item :: tail =>  acc.put(item, acc.getOrElseUpdate(item, 0) + 1); calculate(acc, tail)
  }

}
