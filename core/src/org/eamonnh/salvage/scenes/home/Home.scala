package org.eamonnh.salvage.scenes.home

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonn.salvage.Scene
import org.eamonnh.salvage.scenes.game.Game

class Home extends Scene {
  var ready = false

  override def init(): InputAdapter = new HomeControl(this)

  override def update(delta: Float): Option[Scene] = {
    if (ready) Some(new Game) else None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
  }
}
