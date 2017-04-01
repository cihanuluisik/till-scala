package com.lorinit.till

case class OfferList(offersList:(Item,Offer)*){
  val offer:Map[Item, Offer] = offersList.toMap withDefaultValue Offer(1000000,0)
}
