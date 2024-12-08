package org.eamonnh.salvage.stations

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage.util.{TextureWrapper, Vec2I}

abstract class StationArchetype{
  var sprite: TextureWrapper
  var size: Vec2I
}

class Outpost extends StationArchetype {

  override var sprite: TextureWrapper = TextureWrapper.load("Station1.png")
  override var size: Vec2I = Vec2I(8, 8)
}