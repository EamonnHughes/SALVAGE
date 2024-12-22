package org.eamonnh.salvage.actors.ships

import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.actors.planets.cities.City
import org.eamonnh.salvage.actors.ships.behavior.Behavior
import org.eamonnh.salvage.actors.ships.components.Engine
import org.eamonnh.salvage.cargo.Cargo
import org.eamonnh.salvage.crew.Officer
import org.eamonnh.salvage.scenes.game._
import org.eamonnh.salvage.util._

class Ship extends Actor {
  var arch: ShipArchetype = _
  var engine: Engine = _
  var behavior: Option[Behavior] = None
  var movingSlower: Boolean = false
  var movingFullSpeed: Boolean = false
  var rotatingRight: Boolean = false
  var rotatingLeft: Boolean = false
  var cargo: List[Cargo] = List.empty
  var captain: Officer = _

  var anchorage: Option[Anchorage] = None

  override def deRotAccel = engine.turnDecel
  override def deAccel = arch.deAccel
  override def topSpeed = arch.topSpeed
  override def size = arch.shipClass.size

  override def sprites: List[TextureWrapper] =
    if (anchorage.isEmpty) List(engine.sprite, arch.sprite) else List.empty

  override def init(game: Game): Unit = {}
  override def update(game: Game, delta: Float): Unit = {
    behavior.foreach(_.update(this, game, delta))
    ControlMovement()
    if (anchorage.nonEmpty) {
      anchorage.foreach(a => {
        location = a.location.copy()
      })
    } else {
      DoPhysics()
    }
  }

  def ControlMovement(): Unit = {
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
  }
  def DoPhysics(): Unit = {
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

  def TryToLand(game: Game): Unit = {
    if (
      game.actors.exists(a =>
        a.isInstanceOf[Anchorage] && a.location.distanceFrom(location) < a.size.x / 2
      )
    ) {
      anchorage = Some(
        game.actors
          .collect({ case anchorage: Anchorage => anchorage })
          .minBy(a => a.location.distanceFrom(location))
      )
    }
  }
}
