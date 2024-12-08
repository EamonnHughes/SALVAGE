package org.eamonnh.salvage.actors.ships

import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.actors.ships.components.{Engine, MKI}
import org.eamonnh.salvage.scenes.game._
import org.eamonnh.salvage.util._

abstract class Ship extends Actor {
  var arch: ShipArchetype = _
  var engine: Engine = _
  var movingSlower: Boolean = false
  var movingFullSpeed: Boolean = false
  var rotatingRight: Boolean = false
  var rotatingLeft: Boolean = false

  override def deRotAccel = engine.turnDecel
  override def deAccel = arch.deAccel
  override def topSpeed = arch.topSpeed
  override def size = arch.shipClass.size

  override def sprites: List[TextureWrapper] = List(engine.sprite, arch.sprite)

  override def init(game: Game): Unit = {
  }
  override def update(game: Game, delta: Float): Unit = {
    if (movingSlower) {
      forwardAcc = engine.forwardSpeed * engine.slowdown
    } else if (movingFullSpeed) {
      forwardAcc = engine.forwardSpeed
    } else {
      forwardAcc = 0
    }
    if (rotatingRight) {
      rotVel = -engine.turnSpeed
    } else if (rotatingLeft) {
      rotVel = engine.turnSpeed
    }

    //PHYSICS
    if (
      Math.sqrt(
        (velocity.x * velocity.x) + (velocity.y * velocity.y)
      ) < topSpeed
    ) {
      velocity += Vec2F(
        forwardAcc * Math.cos(rotation).toFloat,
        forwardAcc * Math.sin(rotation).toFloat
      )
    }
    velocity *= deAccel
    rotVel *= deRotAccel
  }
}
