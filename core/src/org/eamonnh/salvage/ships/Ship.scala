package org.eamonnh.salvage.ships

import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.scenes.game._
import org.eamonnh.salvage.ships.components._
import org.eamonnh.salvage.util._

abstract class Ship extends Actor {
  var arch: ShipArchetype = _
  var engine: Engine = _
  var movingForward: Boolean = false
  var movingBrake: Boolean = false
  var rotatingRight: Boolean = false
  var rotatingLeft: Boolean = false

  override def deRotAccel = engine.turnDecel
  override def deAccel = arch.deAccel
  override def topSpeed = arch.topSpeed
  override def size = arch.shipClass.size


  override def sprites: List[TextureWrapper] = List(engine.sprite, arch.sprite)

  override def init(): Unit = {
    location = Vec2F(5, 5)
    arch = new Carc()
    engine = new MKI()
  }
  override def update(game: Game, delta: Float): Unit = {
    if(movingForward) {
      forwardAcc = engine.forwardSpeed
    } else if (movingBrake) {
      forwardAcc = engine.forwardSpeed * engine.brakeRate
    } else {
      forwardAcc = 0
    }
    if(rotatingRight) {
      rotVel = -engine.turnSpeed
    } else if(rotatingLeft) {
      rotVel = engine.turnSpeed
    }
  }
}
