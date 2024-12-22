package org.eamonnh.salvage.actors.ships.behavior

import org.eamonnh.salvage.actors.ships.Ship
import org.eamonnh.salvage.scenes.game.Game

abstract class Behavior {
  def update(ship: Ship, game: Game, delta: Float): Unit
}
