package org.eamonnh.salvage.actors.ships.components

import org.eamonnh.salvage.actors.ships.Ship
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.Vec2F

abstract class Behavior {
  def update(ship: Ship, game: Game): Unit
}

class Convoy extends Behavior {
  override def update(ship: Ship, game: Game): Unit = {
    ship.location = Vec2F(game.player.location.x + 4, game.player.location.y + 4)
    ship.rotation = game.player.rotation
  }
}