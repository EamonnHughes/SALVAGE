package org.eamonnh.salvage.actors

trait Engine {
  var forwardSpeed: Int
  var turnSpeed: Int
  var brakeSpeed: Int
}

case class MKI() extends Engine {

  override var forwardSpeed: Int = 50
  override var turnSpeed: Int = 10
  override var brakeSpeed: Int = 10
}

case class MKII() extends Engine {

  override var forwardSpeed: Int = 30
  override var turnSpeed: Int = 30
  override var brakeSpeed: Int = 30
}