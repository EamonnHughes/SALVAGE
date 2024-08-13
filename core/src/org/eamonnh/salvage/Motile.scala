package org.eamonnh.salvage

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonn.salvage.screenUnit
import org.eamonn.salvage.util.TextureWrapper
import org.eamonnh.salvage.util.Vec2

trait Motile {
var location: Vec2
  var size: Vec2
  def sprite: TextureWrapper
  def draw(batch: PolygonSpriteBatch): Unit = {
    batch.draw(sprite, location.x * screenUnit, location.y * screenUnit, size.x * screenUnit, size.y * screenUnit)
  }
}
