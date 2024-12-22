package org.eamonnh.salvage

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.menus.Menu

abstract class Scene {
  def menu: Option[Menu]
  def init(): InputAdapter
  def update(delta: Float): Option[Scene]
  def render(batch: PolygonSpriteBatch): Unit
  def renderUI(batch: PolygonSpriteBatch): Unit = {
    menu.foreach(m => m.draw(batch))
  }
}
