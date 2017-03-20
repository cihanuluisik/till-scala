package com.lorinit.till

case class Item(name: String, price: Int)

object Item {
  val Orange: Item = Item("orange", 25)
  val Apple: Item = Item("apple", 60)
  val Kiwi: Item = Item("kiwi", 20)
}
