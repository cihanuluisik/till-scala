package com.lorinit.till

case class Offer(threshold: Int, discountAmount: Int)

class TillWithOffer(offers: Map[Item, Offer]) extends Till {

  override def calculate(shoppingList: List[Item]) = {
    super.calculate(shoppingList) - calculateDiscount(shoppingList)
  }

  private def calculateDiscount(list: List[Item]): Int = {
    val itemToCountMap: Map[Item, Int] = list.groupBy(identity).mapValues(_.size)
    itemToCountMap.foldLeft(0)((a, b) => a + (b._2 / findOffer(b._1).threshold) * findOffer(b._1).discountAmount)
  }

  private def findOffer(item: Item): Offer = {
    offers.getOrElse(item, Offer(1, 0))
  }

}