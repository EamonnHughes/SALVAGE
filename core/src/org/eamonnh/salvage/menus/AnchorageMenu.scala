package org.eamonnh.salvage.menus

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.Salvage.Square
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.{Geometry, Salvage, Scene, Text, screenUnit}
import org.eamonnh.salvage.scenes.start.Start
import org.eamonnh.salvage.util.{TextureWrapper, Vec2F}


class AnchorageMenu(scene: Scene) extends Menu(scene) {

  override var items: List[MenuItem] = List(new Background, new LeaveButton)
  override var subMenu: Option[Menu] = None
}

class Background extends MenuItem {

  override def draw(batch: PolygonSpriteBatch): Unit = {
    batch.setColor(0f, 0f, 0f, .4f)
    batch.draw(Salvage.Square, screenUnit * 2, screenUnit * 2, Geometry.ScreenWidth - (screenUnit * 4), Geometry.ScreenHeight - (screenUnit * 4))
    batch.setColor(Color.WHITE)
  }

  override def update(scene: Scene): Unit = {

  }
}

class LeaveButton extends Button {
  override def drawContents(batch: PolygonSpriteBatch): Unit = {
    Text.mediumFont.draw(batch, "Exit", location.x * screenUnit, (location.y + 2) * screenUnit)
  }

  var location: Vec2F = Vec2F( (Geometry.ScreenWidth / screenUnit) - 9,  3 )
  var size: Vec2F = Vec2F(6, 2)
  override var color: Color = Color.GREEN

  override def onPress(scene: Scene): Unit = {
    scene match {
      case game: Game => {
        game.player.anchorage = None
        game.menu = None
      }
    }
  }

  override var frame: TextureWrapper = Square
}