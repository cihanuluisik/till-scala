package com.lorinit.till

case class OfferBuyNGetYFree(item:Item, threshold: Int, discountNumber: Int, var prices: PriceList=PriceList() ) {

  def calculateDiscount(list:List[Item]): Int ={
    val itemToCount: Map[Item, Int] = list.groupBy(identity).mapValues(_.length)
    itemToCount.filterKeys(item == _).map {  i => (prices(i._1) *  (i._2 / threshold) * discountNumber) }.sum
  }
}

