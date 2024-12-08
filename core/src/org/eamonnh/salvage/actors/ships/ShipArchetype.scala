package org.eamonnh.salvage.actors.ships

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.TextureWrapper

abstract class ShipArchetype {
  var sprite: TextureWrapper
  var shipClass: ShipClass
  var deAccel: Float
  var topSpeed: Float
}

class Carc extends ShipArchetype {

  override var sprite: TextureWrapper = TextureWrapper.load("Carcharodon.png")
  override var shipClass: ShipClass = new Fighter()
  override var deAccel: Float = .99f
  override var topSpeed: Float = .5f
}
class Corv extends ShipArchetype {

  override var sprite: TextureWrapper = TextureWrapper.load("Corvette.png")
  override var shipClass: ShipClass = new Fighter()
  override var deAccel: Float = .95f
  override var topSpeed: Float = .65f
}
