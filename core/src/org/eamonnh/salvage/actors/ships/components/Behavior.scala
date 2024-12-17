package org.eamonnh.salvage.actors.ships.components

import org.eamonnh.salvage.actors.ships.Ship
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.Vec2F

abstract class Behavior {
  def update(ship: Ship, game: Game): Unit
}

class Convoy(target: Ship) extends Behavior {
  override def update(ship: Ship, game: Game): Unit = {

    var targetRot = (Math.atan2(target.location.y - ship.location.y, target.location.x - ship.location.x) + (Math.PI * 2)) % (Math.PI*2)
    println(targetRot)
    if (ship.rotation - targetRot > (Math.PI / 4)) {
      ship.rotatingRight = true
      ship.rotatingLeft = false
    } else if (ship.rotation - targetRot < -(Math.PI / 4)) {
      ship.rotatingRight = false
      ship.rotatingLeft = true
    } else {
      ship.rotatingRight = false
      ship.rotatingLeft = false
    }
     if(Math.abs(ship.rotation - targetRot) < (Math.PI / 4)) {
      if(ship.location.distanceFrom(target.location) > 25) {
        ship.movingFullSpeed = true
        ship.movingSlower = false
      } else if (ship.location.distanceFrom(target.location) > 10) {
        ship.movingSlower = true
        ship.movingFullSpeed = false
      } else {
        ship.movingFullSpeed = false
        ship.movingSlower = false
      }
    } else {
      ship.movingFullSpeed = false
      ship.movingSlower = false
    }
  }
}