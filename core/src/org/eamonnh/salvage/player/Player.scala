package org.eamonnh.salvage.player

import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage.actors.ships.{Carc, Ship, ShipArchetype}
import org.eamonnh.salvage.actors.ships.components.Engine
import org.eamonnh.salvage.scenes.game.{Game, GameTriggers}
import org.eamonnh.salvage.util.Vec2F

class Player extends Ship {
  def playerUpdate(game: Game, delta: Float): Unit ={
    if(GameTriggers.Forward) {
      if(GameTriggers.Shift) {
        movingFullSpeed = true
      } else {
        movingSlower = true
      }
    } else {
      movingSlower = false
      movingFullSpeed = false
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

