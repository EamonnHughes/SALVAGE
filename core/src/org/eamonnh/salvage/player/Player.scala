package org.eamonnh.salvage.player

import org.eamonnh.salvage.actors.Motile
import org.eamonnh.salvage.ships.{Archetype, Carc, Ship}
import org.eamonnh.salvage.scenes.game.{Game, GameTriggers}
import org.eamonnh.salvage.ships.components.Engine
import org.eamonnh.salvage.util.Vec2F

class Player extends Ship {
  override def sprite = arch.sprite
  def playerUpdate(game: Game, delta: Float): Unit ={
    if(GameTriggers.Forward) {
      if(GameTriggers.Shift) {
        movingBrake = true
      } else {
        movingForward = true
      }
    } else {
      movingForward = false
      movingBrake = false
    }
    if(GameTriggers.Right) {
      rotatingRight = true
      rotatingLeft = false
    } else if(GameTriggers.Left) {
      rotatingLeft = true
      rotatingRight = false
    } else {
      rotatingRight = false
      rotatingLeft = false
    }
  }
}

