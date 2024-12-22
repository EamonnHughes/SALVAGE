package org.eamonnh.salvage.actors.ships.behavior

import org.eamonnh.salvage.actors.ships.Ship
import org.eamonnh.salvage.scenes.game.Game

class Convoy(target: Ship) extends Behavior {
  override def update(ship: Ship, game: Game, delta: Float): Unit = {
    if (target.anchorage.isEmpty) {
      def targetRot = {
        if (ship.location.distanceFrom(target.location) < 5) {
          target.rotation
        } else {
          (Math.atan2(
            target.location.y - ship.location.y,
            target.location.x - ship.location.x
          ) + (Math.PI * 2)) % (Math.PI * 2)
        }
      }

      var t1 = (ship.rotation - targetRot + (Math.PI * 2)) % (Math.PI * 2)
      var t2 = (targetRot - ship.rotation + (Math.PI * 2)) % (Math.PI * 2)
      if (Math.min(t1, t2) > (Math.PI / 16)) {
        if (t1 < t2) {
          ship.rotatingRight = true
          ship.rotatingLeft = false
        } else {
          ship.rotatingLeft = true
          ship.rotatingRight = false
        }
      } else {
        ship.rotatingRight = false
        ship.rotatingLeft = false
      }
      if (Math.min(t1, t2) < (Math.PI / 4)) {
        if (ship.location.distanceFrom(target.location) > 15) {
          ship.movingFullSpeed = true
          ship.movingSlower = false
        } else if (ship.location.distanceFrom(target.location) > 5) {
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
    } else {
      ship.movingFullSpeed = false
      ship.movingSlower = false
      ship.rotatingRight = false
      ship.rotatingLeft = false
    }
  }
}
