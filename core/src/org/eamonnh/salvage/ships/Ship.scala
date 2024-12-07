package org.eamonnh.salvage.ships

import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.scenes.game._
import org.eamonnh.salvage.ships.components._
import org.eamonnh.salvage.util._

abstract class Ship extends Motile {
  var arch: Archetype = _
  var engine: Engine = _
  var movingForward: Boolean = false
  var braking: Boolean = false
  var rotatingRight: Boolean = false
  var rotatingLeft: Boolean = false

  override def init(): Unit = {
    location = Vec2F(5, 5)
    arch = new Carc()
    engine = new MKII()
    size = arch.shipClass.size
    deRotAccel = engine.turnDecel
    deAccel = engine.decel
  }
  override def update(game: Game, delta: Float): Unit = {
    if(movingForward) {

    } else if(braking) {

    }
    if(rotatingRight) {
      rotVel = -engine.turnSpeed
    } else if(rotatingLeft) {
      rotVel = engine.turnSpeed
    }
  }
}
