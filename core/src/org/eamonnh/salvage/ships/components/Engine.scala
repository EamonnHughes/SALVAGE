package org.eamonnh.salvage.ships.components

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.TextureWrapper

abstract class Engine {
  var forwardSpeed: Float
  var turnSpeed: Float
  var turnDecel: Float
  var brakeRate: Float
  var sprite: TextureWrapper
}

class MKI extends Engine {

  override var forwardSpeed = .125f
  override var turnSpeed = .05f
  override var turnDecel = .95f
  override var brakeRate = .1f
  override var sprite: TextureWrapper = TextureWrapper.load("EngineMKI.png")
}

class MKII extends Engine {

  override var forwardSpeed = .05f
  override var turnSpeed = .1f
  override var turnDecel = .5f
  override var brakeRate = .1f
  override var sprite: TextureWrapper = TextureWrapper.load("EngineMKII.png")
}