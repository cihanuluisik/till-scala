package com.lorinit.till

import scala.annotation.tailrec
import scala.collection.mutable

class Till(priceList: Item => Int, offers: Item => Offer) {

  def calculate(shoppingList: List[Item]) :Int = calculate(mutable.Map(), shoppingList)

  @tailrec
  private def calculate(acc: mutable.Map[Item, Int], list: List[Item]): Int = list match {
    case Nil if acc.isEmpty => 0
    case Nil => acc.foldLeft(0)((subtotal, kv) => subtotal + priceList(kv._1) * kv._2 - (kv._2 / offers(kv._1).threshold) * offers(kv._1).discountAmount)
    case item :: tail =>  acc.put(item, acc.getOrElseUpdate(item, 0) + 1); calculate(acc, tail)
  }

}
