package org.eamonnh.salvage.ships

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.TextureWrapper

abstract class Archetype {
  var sprite: TextureWrapper
  var shipClass: ShipClass
  var deAccel: Float
  var topSpeed: Float
}

class Carc extends Archetype {

  override var sprite: TextureWrapper = TextureWrapper.load("Carcharodon.png")
  override var shipClass: ShipClass = new Fighter()
  override var deAccel: Float = .99f
  override var topSpeed: Float = .5f
}