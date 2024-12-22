package org.eamonnh.salvage.cargo

abstract class CargoType {
  var price: Int
  var volume: Float
}

class Iron extends CargoType {
  override var price: Int = 10
  override var volume: Float = 2
}
class Microchips extends CargoType {
  override var price: Int = 20
  override var volume: Float = .5f
}
