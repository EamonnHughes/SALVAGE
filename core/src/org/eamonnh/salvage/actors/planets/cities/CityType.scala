package org.eamonnh.salvage.actors.planets.cities

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.{TextureWrapper, Vec2I}

abstract class CityType {
  var size: Vec2I
  var sprite: TextureWrapper
}

class CityI extends CityType {
  override var size: Vec2I = Vec2I(4, 4)
  override var sprite: TextureWrapper = TextureWrapper.load("City1.png")
}
