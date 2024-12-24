package org.eamonnh.salvage.actors.planets

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.{TextureWrapper, Vec2I}

abstract class PlanetClass {
  var sprite: TextureWrapper
  var size: Vec2I
  var view: TextureWrapper
}

class EarthlikeMedium extends PlanetClass {

  override var sprite: TextureWrapper =
    TextureWrapper.load("EarthlikePlanetMedium.png")
  override var size: Vec2I = Vec2I(48, 48)
  override var view: TextureWrapper = TextureWrapper.load("BarrenCityBackground.png")
}

class BarrenSmall extends PlanetClass {

  override var sprite: TextureWrapper =
    TextureWrapper.load("BarrenPlanetSmall.png")
  override var size: Vec2I = Vec2I(32, 32)
  override var view: TextureWrapper = TextureWrapper.load("BarrenCityBackground.png")
}

class MoonTiny extends PlanetClass {

  override var sprite: TextureWrapper = TextureWrapper.load("MoonTiny.png")
  override var size: Vec2I = Vec2I(8, 8)
  override var view: TextureWrapper = TextureWrapper.load("MoonCityBackground.png")
}
