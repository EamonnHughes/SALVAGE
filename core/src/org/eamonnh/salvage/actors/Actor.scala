package org.eamonnh.salvage.actors

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.screenUnit
import org.eamonnh.salvage.util._

abstract class Actor {
  var location: Vec2F = Vec2F(0, 0)
  var velocity: Vec2F = Vec2F(0, 0)
  var name: String = ""
  var lifetime = 0f
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
      batch.draw(
        sprite,
        (location.x - (size.x.toFloat / 2)) * screenUnit,
        (location.y - (size.y.toFloat / 2)) * screenUnit,
        size.x * screenUnit / 2,
        size.y * screenUnit / 2,
        size.x * screenUnit,
        size.y * screenUnit,
        1,
        1,
        ((rotation / (Math.PI * 2)) * 360).toInt,
        0,
        0,
        size.x * 16,
        size.y * 16,
        false,
        false
      )
    })
  }
  def realUpdate(game: Game, delta: Float): Unit = {
    update(game, delta)
    location += velocity * delta
    rotation += rotVel * delta
    lifetime += delta
  }
  def update(game: Game, delta: Float): Unit
  def init(game: Game): Unit
}
