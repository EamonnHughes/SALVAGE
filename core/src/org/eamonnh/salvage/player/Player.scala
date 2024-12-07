package org.eamonnh.salvage.player

import org.eamonnh.salvage.actors.{Archetype, Carc, Engine, Motile, Ship}
import org.eamonnh.salvage.scenes.game.{Game, GameTriggers}
import org.eamonnh.salvage.util.Vec2

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

