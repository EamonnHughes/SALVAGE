package org.eamonnh.salvage.menus

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.Salvage.{Square, spaceBG}
import org.eamonnh.salvage.actors.Anchorage
import org.eamonnh.salvage.actors.planets.Planet
import org.eamonnh.salvage.actors.planets.cities.City
import org.eamonnh.salvage.actors.stations.Station
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.{TextureWrapper, Vec2F}
import org.eamonnh.salvage.{Geometry, Salvage, Scene, Text, screenUnit}

class AnchorageMenu(scene: Scene) extends Menu(scene) {

  override var items: List[MenuItem] = {
    scene match {
      case game: Game => {
        List(
          new Background,
          new LeaveButton,
          new AnchorageView(game.player.anchorage.head)
        )
      }
      case default => List.empty
    }
  }
  override var subMenu: Option[Menu] = None
}

class Background extends MenuItem {

  override def draw(batch: PolygonSpriteBatch): Unit = {
    batch.setColor(.2f, .2f, .2f, 1f)
    batch.draw(
      Salvage.Square,
      Geometry.MenuStart.x + (screenUnit * 2),
      screenUnit * 2,
      Geometry.ScreenHeight - (screenUnit * 4),
      Geometry.ScreenHeight - (screenUnit * 4)
    )
    batch.setColor(Color.WHITE)
  }

  override def update(scene: Scene): Unit = {}
}

class AnchorageView(anchorage: Anchorage) extends MenuItem {
  var location: Vec2F = Vec2F((Geometry.MenuEnd.x / screenUnit) - 9, 3)
  override def draw(batch: PolygonSpriteBatch): Unit = {
    var bgPic: TextureWrapper = null
    var fgPic: TextureWrapper = null
    anchorage match {
      case city: City => {
        city.parent match {
          case planet: Planet => bgPic = planet.pClass.view
          case default        => bgPic = spaceBG
        }
        fgPic = city.cityType.view
      }
      case station: Station => {
        fgPic = station.arch.view
        bgPic = spaceBG
      }
    }
    var pics: List[TextureWrapper] = List(spaceBG, bgPic, fgPic)
    batch.setColor(.3f, .3f, .3f, 1f)
    batch.draw(
      Salvage.Square,
      ((Geometry.ScreenWidth / (screenUnit * 2)) - 8.5f) * screenUnit,
      ((Geometry.ScreenHeight / (screenUnit * 2)) + 4.5f) * screenUnit,
      screenUnit * 17,
      screenUnit * 9
    )
    batch.setColor(Color.WHITE)
    pics.foreach(pic =>
      batch.draw(
        pic,
        ((Geometry.ScreenWidth / (screenUnit * 2)) - 8) * screenUnit,
        ((Geometry.ScreenHeight / (screenUnit * 2)) + 5) * screenUnit,
        screenUnit * 16,
        screenUnit * 8
      )
    )
  }
  override def update(scene: Scene): Unit = {}
}

class LeaveButton extends Button {
  override def drawContents(batch: PolygonSpriteBatch): Unit = {
    Text.mediumFont.draw(
      batch,
      "Exit",
      (location.x + .1f) * screenUnit,
      (location.y + 1.9f) * screenUnit
    )
  }

  var location: Vec2F = Vec2F((Geometry.MenuEnd.x / screenUnit) - 9, 3)
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
