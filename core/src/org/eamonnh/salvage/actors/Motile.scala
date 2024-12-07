package org.eamonnh.salvage.actors

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.screenUnit
import org.eamonnh.salvage.util._
import org.eamonnh.salvage.scenes.game.Game

abstract class Motile {
  var location: Vec2F = Vec2F(0, 0)
  var velocity: Vec2F = Vec2F(0, 0)
  var size: Vec2I = Vec2I(0, 0)
  var rotation: Float = 0
  def sprite: TextureWrapper
  def draw(batch: PolygonSpriteBatch): Unit = {
    batch.draw(sprite, location.x * screenUnit, location.y * screenUnit, size.x * screenUnit / 2, size.y * screenUnit / 2, size.x * screenUnit, size.y * screenUnit, 1, 1, rotation, 0, 0, size.x * 16, size.y * 16, false, false)
  }
  def realUpdate(game: Game, delta: Float): Unit = {
    update(game, delta)
    location += velocity
    velocity *= .9f
  }
  def update(game: Game, delta: Float): Unit
  def init(): Unit
}
