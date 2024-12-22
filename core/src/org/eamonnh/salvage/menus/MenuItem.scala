package org.eamonnh.salvage.menus

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.util.{TextureWrapper, Vec2F}
import org.eamonnh.salvage.{Geometry, Scene, screenUnit}

abstract class MenuItem {
  def draw(batch: PolygonSpriteBatch): Unit
  def update(scene: Scene)
}

abstract class Button extends MenuItem {
  var location: Vec2F
  var size: Vec2F
  var color: Color
  var frame: TextureWrapper
  override def draw(batch: PolygonSpriteBatch): Unit = {
    batch.setColor(color)
    batch.draw(
      frame,
      location.x * screenUnit,
      location.y * screenUnit,
      size.x * screenUnit,
      size.y * screenUnit
    )
    batch.setColor(Color.WHITE)
    drawContents(batch)
  }

  override def update(scene: Scene): Unit = {
    if (
      (Gdx.input.getX() > location.x * screenUnit && Gdx.input
        .getX() < (location.x + size.x) * screenUnit) &&
      (Geometry.ScreenHeight - Gdx.input
        .getY() > location.y * screenUnit && Geometry.ScreenHeight - Gdx.input
        .getY() < (location.y + size.y) * screenUnit)
    ) {
      onPress(scene)
    }
  }
  def onPress(scene: Scene): Unit
  def drawContents(batch: PolygonSpriteBatch): Unit
}
