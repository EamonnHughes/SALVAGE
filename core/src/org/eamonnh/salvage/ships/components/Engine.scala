package org.eamonnh.salvage.ships.components

abstract class Engine {
  var forwardSpeed: Float
  var turnSpeed: Float
  var turnDecel: Float
  var brakeSpeed: Float
  var decel: Float
}

class MKI extends Engine {

  override var forwardSpeed = 5.0f
  override var turnSpeed = .025f
  override var brakeSpeed = .025f
  override var turnDecel = .95f
  override var decel = .9f
}

class MKII extends Engine {

  override var forwardSpeed = 3.0f
  override var turnSpeed = .05f
  override var brakeSpeed = .05f
  override var turnDecel = .5f
  override var decel = .9f
}