package org.eamonnh.salvage.actors.ships.components

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.actors.ships.{Destroyer, Fighter, ShipClass}
import org.eamonnh.salvage.util.TextureWrapper

abstract class Engine {
  var forwardSpeed: Float
  var turnSpeed: Float
  var turnDecel: Float
  var slowdown: Float
  var sprite: TextureWrapper
  var classAllowed: ShipClass
}

class MKI extends Engine {

  override var forwardSpeed = 12.5f
  override var turnSpeed = 5f
  override var turnDecel = .95f
  override var slowdown = .1f
  override var sprite: TextureWrapper = TextureWrapper.load("EngineMKI.png")
  override var classAllowed: ShipClass = new Fighter
}

class MKII extends Engine {

  override var forwardSpeed = 5f
  override var turnSpeed = 10f
  override var turnDecel = .5f
  override var slowdown = .1f
  override var sprite: TextureWrapper = TextureWrapper.load("EngineMKII.png")
  override var classAllowed: ShipClass = new Fighter
}

class Tokamak extends Engine {

  override var forwardSpeed = 5f
  override var turnSpeed = 10f
  override var turnDecel = .5f
  override var slowdown = .1f
  override var sprite: TextureWrapper = TextureWrapper.load("EngineTokamak.png")
  override var classAllowed: ShipClass = new Destroyer
}
