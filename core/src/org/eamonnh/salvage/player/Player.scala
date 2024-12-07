package org.eamonnh.salvage.player

import org.eamonnh.salvage.actors.Motile
import org.eamonnh.salvage.ships.{Archetype, Carc, Ship}
import org.eamonnh.salvage.scenes.game.{Game, GameTriggers}
import org.eamonnh.salvage.ships.components.Engine
import org.eamonnh.salvage.util.Vec2F

class Player extends Ship {
  override def sprite = arch.sprite
  override def update(game: Game, delta: Float): Unit ={
    if(GameTriggers.playerMovingForward) {
      movingForward = true
      braking = false
    }
    if(GameTriggers.playerMovingBack) {
      braking = true
      movingForward = false
    }
  }
}

