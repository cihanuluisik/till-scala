package com.lorinit.till

case class OfferCheapestFree(items:List[Item], threshold: Int, discountAmount: Int, var prices: PriceList=PriceList()) {
  def calculateDiscount(list:List[Item]): Int ={
    val sorted: List[Item] = list.filter(items.contains(_)) .sortBy(prices(_))
    sorted.take(sorted.size / 2).map(prices(_)).sum
  }
}
