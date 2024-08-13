package org.eamonn.salvage
package scenes

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonn.salvage.Scene
import org.eamonnh.salvage.scenes.inLevel

class Home extends Scene {

  override def init(): InputAdapter = new HomeControl(this)

  override def update(delta: Float): Option[Scene] = {
    Some(new inLevel)
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
  }
}
