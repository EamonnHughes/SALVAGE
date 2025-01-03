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
  def baseSize: Vec2I = Vec2I(0, 0)
  var scale = 1f
  def scaledSize: Vec2F = Vec2F(baseSize.x * scale, baseSize.y * scale)
  var rotation: Float = 0
  def sprites: List[TextureWrapper]
  def draw(batch: PolygonSpriteBatch): Unit = {
    sprites.foreach(sprite => {
      batch.draw(
        sprite,
        (location.x - (scaledSize.x.toFloat / 2)) * screenUnit,
        (location.y - (scaledSize.y.toFloat / 2)) * screenUnit,
        scaledSize.x * screenUnit / 2,
        scaledSize.y * screenUnit / 2,
        scaledSize.x * screenUnit,
        scaledSize.y * screenUnit,
        1,
        1,
        ((rotation / (Math.PI * 2)) * 360).toInt,
        0,
        0,
        baseSize.x * 16,
        baseSize.y * 16,
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
