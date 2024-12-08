package org.eamonnh.salvage.planet

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.{TextureWrapper, Vec2I}

abstract class PlanetClass{
  var sprite: TextureWrapper
  var size: Vec2I
}

class BarrenSmall extends PlanetClass {

  override var sprite: TextureWrapper = TextureWrapper.load("BarrenPlanetSmall.png")
  override var size: Vec2I = Vec2I(32, 32)
}

class MoonTiny extends PlanetClass {

  override var sprite: TextureWrapper = TextureWrapper.load("MoonTiny.png")
  override var size: Vec2I = Vec2I(8, 8)
}