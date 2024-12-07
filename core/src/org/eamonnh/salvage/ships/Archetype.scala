package org.eamonnh.salvage.ships

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.TextureWrapper

abstract class Archetype {
  var sprite: TextureWrapper
  var shipClass: ShipClass
}

class Carc extends Archetype {

  override var sprite: TextureWrapper = TextureWrapper.load("Carcharodon.png")
  override var shipClass: ShipClass = new Fighter()
}