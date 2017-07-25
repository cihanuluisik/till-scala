package com.lorinit.till


case class PriceList(priceList:(Item,Int)*){
  val price:Map[Item, Int] = priceList.toMap withDefaultValue 0
  def apply(i:Item)= price(i)
}

