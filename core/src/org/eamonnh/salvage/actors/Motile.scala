package org.eamonnh.salvage.actors

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.screenUnit
import org.eamonnh.salvage.util.TextureWrapper
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.Vec2

abstract class Motile {
  var location: Vec2 = Vec2(0, 0)
  var velocity: Vec2 = Vec2(0, 0)
  var size: Vec2 = Vec2(0, 0)
  var rotation: Double = 0
  def sprite: TextureWrapper
  def draw(batch: PolygonSpriteBatch): Unit = {
    batch.draw(sprite, location.x * screenUnit, location.y * screenUnit, 0, 0, size.x * screenUnit, size.y * screenUnit, 1, 1, 1, 0, 0, 64, 64, false, false)
  }
  def realUpdate(game: Game, delta: Float): Unit = {
    update(game, delta)
    location += velocity
    velocity *= .9f
  }
  def update(game: Game, delta: Float): Unit
}
