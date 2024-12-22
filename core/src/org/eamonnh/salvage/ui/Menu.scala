package org.eamonnh.salvage.ui

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.Scene

abstract class Menu(scene: Scene) {
  var items: List[MenuItem]
  var subMenu: Option[Menu]
  def update(): Unit = {
    subMenu.foreach(_.update())
  }
  def draw(batch: PolygonSpriteBatch): Unit = {
    items.foreach(_.draw(batch))
    subMenu.foreach(_.draw(batch))
  }
}
