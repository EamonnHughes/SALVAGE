package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Matrix4
import org.eamonnh.salvage._
import org.eamonnh.salvage.actors.planets.Planet
import org.eamonnh.salvage.actors.planets.cities.City
import org.eamonnh.salvage.actors.ships.Ship
import org.eamonnh.salvage.actors.stations.Station
import org.eamonnh.salvage.actors.suns.Sun
import org.eamonnh.salvage.actors.{Actor, Anchorage, Orbital}
import org.eamonnh.salvage.generation.Generator
import org.eamonnh.salvage.menus.{AnchorageMenu, Menu}
import org.eamonnh.salvage.player._
import org.eamonnh.salvage.util.Vec2F

class Game extends Scene {
  var menu: Option[Menu] = None
  def player: Player = {
    var p: List[Player] = List.empty
    ships.foreach {
      case player: Player => p = player :: p
      case default        =>
    }
    p.head
  }

  var suns: List[Sun] = List.empty
  var planets: List[Planet] = List.empty
  var stations: List[Station] = List.empty
  var cities: List[City] = List.empty
  var ships: List[Ship] = List.empty
  def actors: List[Actor] = suns ::: planets ::: stations ::: cities ::: ships
  def anchorages: List[Anchorage] = {
    var a: List[Anchorage] = List.empty
    actors.foreach({
      case anchorage: Anchorage => a = anchorage :: a
      case default =>
    })
    a
  }

  def cameraLoc: Vec2F = if (player.anchorage.nonEmpty) {
    Vec2F(
      player.anchorage.head.location.x * screenUnit * zoom - (Geometry.ScreenWidth / 2),
      player.anchorage.head.location.y * screenUnit * zoom - (Geometry.ScreenHeight / 2)
    )
  } else {
    Vec2F(
      player.location.x * screenUnit * zoom - (Geometry.ScreenWidth / 2),
      player.location.y * screenUnit * zoom - (Geometry.ScreenHeight / 2)
    )
  }

  override def init(): InputAdapter = {

    Generator.game = this

    Generator.generateSolarSystem(Vec2F(0, 0))
    Generator.generateSolarSystem(Vec2F(1024, 1024))
    Generator.generatePlayer()
    for(i <- 0 until 40) {
      Generator.generateTrader()
    }

    actors.foreach(m => m.init(this))
    actors.foreach {
      case orbital: Orbital => orbital.orbitInit()
      case default          =>
    }
    new GameController(this)
  }

  override def update(delta: Float): Option[Scene] = {
    if (menu.isEmpty && player.anchorage.nonEmpty)
      menu = Some(new AnchorageMenu(this))
    if (menu.isEmpty) realUpdate(delta)
    None
  }
  def realUpdate(delta: Float): Unit = {
    player.playerUpdate(this, delta)
    actors.foreach {
      case orbital: Orbital => orbital.orbitUpdate()
      case default          =>
    }
    actors.foreach(m => {
      m.rotation = (m.rotation + Math.PI * 2).toFloat % (Math.PI * 2).toFloat
      m.realUpdate(this, delta)
      m.rotation = (m.rotation + Math.PI * 2).toFloat % (Math.PI * 2).toFloat
    })
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
    batch.setTransformMatrix(
      new Matrix4().trn(-cameraLoc.x, -cameraLoc.y, 0).scl(zoom)
    )
    actors.foreach(m => m.draw(batch))
    batch.flush()
    batch.setTransformMatrix(new Matrix4())
    drawUI(batch)
  }

  def drawUI(batch: PolygonSpriteBatch): Unit = {
    batch.setColor(0f, 0f, 0f, .1f)
    batch.draw(Salvage.Square, 0, 0, Geometry.ScreenWidth, screenUnit * 1.5f)
    batch.setColor(Color.WHITE)
    var vicinity = "Space, near " + player.nearestSun(this).name
    player.anchorage.foreach {
      case city: City => vicinity = city.name + ", " + city.parent.name + ", near " + player.nearestSun(this).name
      case station: Station => vicinity = station.name + ", near " + player.nearestSun(this).name
    }
    var resources = player.captain.credits + " credits"
    Text.smallFont.setColor(Color.WHITE)
    Text.smallFont.draw(batch, vicinity + " | " + resources, 0, screenUnit)
    Text.smallFont.setColor(Color.BLACK)
  }
}
