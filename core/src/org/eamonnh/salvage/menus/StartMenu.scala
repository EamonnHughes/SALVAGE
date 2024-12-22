package org.eamonnh.salvage.menus

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.Salvage.Square
import org.eamonnh.salvage.scenes.start.Start
import org.eamonnh.salvage.util.{TextureWrapper, Vec2F}
import org.eamonnh.salvage.{Geometry, Scene, Text, screenUnit}

class StartMenu(scene: Scene) extends Menu(scene) {

  override var items: List[MenuItem] = List(new StartButton)
  override var subMenu: Option[Menu] = None
}
class StartButton extends Button {
  override def drawContents(batch: PolygonSpriteBatch): Unit = {
    Text.mediumFont.draw(
      batch,
      "Play",
      location.x * screenUnit,
      (location.y + 2) * screenUnit
    )
  }

  var location: Vec2F = Vec2F(
    (Geometry.ScreenWidth / (2 * screenUnit)) - 3,
    (Geometry.ScreenHeight / (2 * screenUnit)) - 1
  )
  var size: Vec2F = Vec2F(6, 2)
  override var color: Color = Color.GREEN

  override def onPress(scene: Scene): Unit = {
    scene match {
      case start: Start => start.ready = true
    }
  }

  override var frame: TextureWrapper = Square
}
