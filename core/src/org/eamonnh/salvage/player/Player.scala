package org.eamonnh.salvage.player

import org.eamonnh.salvage.Motile
import org.eamonnh.salvage.scenes.game.{Game, GameTriggers}
import org.eamonnh.salvage.util.Vec2

class Player extends Motile{

  override var location: Vec2 = _
  var archetype: Archetype = _
  var speed = Vec2(10, 20)

  override def sprite = archetype.sprite

  override var size: Vec2 = Vec2(4, 4)

  override def update(game: Game, delta: Float): Unit ={
    if(GameTriggers.playerMovingUp) velocity.y = speed.y*delta
    if(GameTriggers.playerMovingDown) velocity.y =  - speed.y*delta
  }

  override var velocity: Vec2 = Vec2(0, 0)
}

