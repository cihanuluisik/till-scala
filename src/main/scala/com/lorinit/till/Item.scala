package com.lorinit.till

case class Item(name: String, price: Int)

object Item {
  def Orange: Item = Item("orange", 25)
  def Apple: Item = Item("apple", 60)
}
