package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Matrix4
import org.eamonnh.salvage._
import org.eamonnh.salvage.actors.planets.cities.{City, CityI}
import org.eamonnh.salvage.actors.{Actor, Orbital}
import org.eamonnh.salvage.actors.planets.{BarrenSmall, MoonTiny, Planet}
import org.eamonnh.salvage.actors.ships.{Carc, Corv, Ship, Vasa}
import org.eamonnh.salvage.actors.ships.components.{Convoy, MKI, MKII, Tokamak}
import org.eamonnh.salvage.actors.stations.{Outpost, Station}
import org.eamonnh.salvage.actors.suns.{Sun, SunI}
import org.eamonnh.salvage.generation.Generator
import org.eamonnh.salvage.player._
import org.eamonnh.salvage.util.{Vec2F, Vec2I}

class Game extends Scene {

  def player: Player = {
    var p: List[Player] = List.empty
    ships.foreach {
      case player: Player => p = player :: p
      case default =>
    }
    p.head
  }

  var suns: List[Sun] = List.empty
  var planets: List[Planet] = List.empty
  var stations: List[Station] = List.empty
  var cities: List[City] = List.empty
  var ships: List[Ship] = List.empty
  def actors: List[Actor] = suns ::: planets ::: stations ::: cities ::: ships

  def cameraLoc: Vec2F = Vec2F(
    player.location.x * screenUnit * zoom - (Geometry.ScreenWidth / 2),
    player.location.y * screenUnit * zoom - (Geometry.ScreenHeight / 2)
  )
  override def init(): InputAdapter = {

    Generator.game = this

    Generator.generateSolarSystem(Vec2F(0, 0))
    Generator.generateSolarSystem(Vec2F(1024, 1024))
    Generator.generatePlayer()

    actors.foreach(m => m.init(this))
    actors.foreach {
      case orbital: Orbital => orbital.orbitInit()
      case default          =>
    }
    new GameController(this)
  }

  override def update(delta: Float): Option[Scene] = {
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
    None
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
    batch.draw(Salvage.Square, 0, 0, Geometry.ScreenWidth, screenUnit)
    var vicinity = "Space, near " + player.nearestSun(this).name
    player.anchorage.foreach(a => {
      vicinity = a.name + ", " + a.parent.name + ", near " + player.nearestSun(this).name
    })
    Text.smallFont.setColor(Color.BLACK)
    Text.smallFont.draw(batch, vicinity, 0, screenUnit)
  }
}
