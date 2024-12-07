package org.eamonnh.salvage.actors

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.screenUnit
import org.eamonnh.salvage.util._
import org.eamonnh.salvage.scenes.game.Game

abstract class Motile {
  var location: Vec2F = Vec2F(0, 0)
  var velocity: Vec2F = Vec2F(0, 0)
  def topSpeed: Float = 0f
  def deAccel: Float = .9f
  var rotVel: Float = 0
  var forwardAcc: Float = 0
  def deRotAccel: Float = .5f
  def size: Vec2I = Vec2I(0, 0)
  var rotation: Float = 0
  def sprites: List[TextureWrapper]
  def draw(batch: PolygonSpriteBatch): Unit = {
    sprites.foreach(sprite => {
      batch.draw(sprite, location.x * screenUnit, location.y * screenUnit, size.x * screenUnit / 2.5f, size.y * screenUnit / 2, size.x * screenUnit, size.y * screenUnit, 1, 1, ((rotation / (Math.PI * 2)) * 360).toInt, 0, 0, size.x * 16, size.y * 16, false, false)
    })
  }
  def realUpdate(game: Game, delta: Float): Unit = {
    update(game, delta)
    if (Math.sqrt((velocity.x * velocity.x) + (velocity.y * velocity.y)) < topSpeed) {
      velocity += Vec2F(
        forwardAcc * Math.cos(rotation).toFloat,
        forwardAcc * Math.sin(rotation).toFloat
      )
  }
    location += velocity
    velocity *= deAccel
    rotation += rotVel
    rotVel *= deRotAccel
  }
  def update(game: Game, delta: Float): Unit
  def init(): Unit
}
