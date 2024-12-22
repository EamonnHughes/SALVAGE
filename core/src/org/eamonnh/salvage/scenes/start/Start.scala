package org.eamonnh.salvage.scenes.start

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.Scene
import org.eamonnh.salvage.menus.StartMenu
import org.eamonnh.salvage.scenes.game.Game

class Start extends Scene {
  var ready = false
  var menu: Option[StartMenu] = Some(new StartMenu(this))
  override def init(): InputAdapter = new StartControl(this)

  override def update(delta: Float): Option[Scene] = {
    if (ready) Some(new Game) else None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {}
}
