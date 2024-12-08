package org.eamonnh.salvage.actors.suns

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.{TextureWrapper, Vec2I}

abstract class SunClass {
  var sprite: TextureWrapper
  var size: Vec2I
}

class SunI extends SunClass {
  override var sprite: TextureWrapper = TextureWrapper.load("Sun1.png")
  override var size: Vec2I = Vec2I(128, 128)
}
