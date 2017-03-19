package com.lorinit.till

class Till {
  def calculate(shoppingList: List[Item]) = shoppingList.foldLeft(0) ( _ + _.price)
}
