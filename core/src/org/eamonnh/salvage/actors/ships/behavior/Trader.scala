package org.eamonnh.salvage.actors.ships.behavior

import org.eamonnh.salvage.actors.Anchorage
import org.eamonnh.salvage.actors.planets.cities.City
import org.eamonnh.salvage.actors.ships.Ship
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.Vec2F

class Trader extends Behavior {
  var target: Option[Anchorage] = None
  override def update(ship: Ship, game: Game): Unit = {
    if(target.isEmpty) {
      target = Some(game.anchorages((Math.random() * game.anchorages.length).toInt))
    }
    def targetRot = {
      (Math.atan2(
        target.head.location.y - ship.location.y,
        target.head.location.x - ship.location.x
      ) + (Math.PI * 2)) % (Math.PI * 2)
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
        target = Some(game.anchorages((Math.random() * game.anchorages.length).toInt))
      }
    } else {
      ship.rotatingRight = false
      ship.rotatingLeft = false
    }
    if (Math.min(t1, t2) < (Math.PI / 4)) {
      if (ship.location.distanceFrom(target.head.location) > 256) {
        ship.movingFullSpeed = true
        ship.movingSlower = false
      } else if (ship.location.distanceFrom(target.head.location) > 1) {
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
