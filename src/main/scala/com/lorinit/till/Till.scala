package com.lorinit.till

class Till(prices: PriceList) {

  var offerBuyNGetYFree: OfferBuyNGetYFree = _
  var offerCheapestFree: OfferCheapestFree = _

  def calculate(list: List[Item]) = list match {
    case l: List[Item] if (l.length > 0) =>
          var sum: Int = list.map(prices(_)).sum  // sum without any offer
          sum -= offerBuyNGetYFree.calculateDiscount(list)
          sum -= offerCheapestFree.calculateDiscount(list)
          sum
    case _ => 0
  }

  def withOffers(oCheapestFree: OfferCheapestFree, oBuyNGetYFree: OfferBuyNGetYFree) = {
    oBuyNGetYFree.prices = this.prices;
    this.offerBuyNGetYFree = oBuyNGetYFree;
    oCheapestFree.prices = this.prices;
    this.offerCheapestFree = oCheapestFree;
    this
  }
}

object Till {
  def withPrices(priceTuples: (Item, Int)*) = new Till(new PriceList(priceTuples: _*))
}
