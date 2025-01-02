package org.eamonnh.salvage.actors.planets.cities

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.{TextureWrapper, Vec2I}

abstract class CityType {
  var size: Vec2I
  var sprite: TextureWrapper
  var view: TextureWrapper
}

class CityI extends CityType {
  override var size: Vec2I = Vec2I(4, 4)
  override var sprite: TextureWrapper = TextureWrapper.load("City1.png")
  override var view: TextureWrapper = TextureWrapper.load("City1Picture.png")
}

class CityII extends CityType {
  override var size: Vec2I = Vec2I(2, 2)
  override var sprite: TextureWrapper = TextureWrapper.load("City2.png")
  override var view: TextureWrapper = TextureWrapper.load("City2Picture.png")
}

class PolarFortressI extends CityType {
  override var size: Vec2I = Vec2I(3, 3)
  override var sprite: TextureWrapper = TextureWrapper.load("PolarFortress1.png")
  override var view: TextureWrapper = TextureWrapper.load("PolarFortress1Picture.png")
}


class Vault extends CityType {
  override var size: Vec2I = Vec2I(1, 1)
  override var sprite: TextureWrapper = TextureWrapper.load("Vault.png")
  override var view: TextureWrapper = TextureWrapper.load("VaultPicture.png")
}
