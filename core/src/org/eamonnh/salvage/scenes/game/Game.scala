package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Matrix4
import org.eamonnh.salvage._
import org.eamonnh.salvage.actors.planets.cities.{City, CityI}
import org.eamonnh.salvage.actors.{Actor, Orbital}
import org.eamonnh.salvage.actors.planets.{BarrenSmall, MoonTiny, Planet}
import org.eamonnh.salvage.actors.ships.Carc
import org.eamonnh.salvage.actors.ships.components.MKI
import org.eamonnh.salvage.actors.stations.{Outpost, Station}
import org.eamonnh.salvage.actors.suns.{Sun, SunI}
import org.eamonnh.salvage.player._
import org.eamonnh.salvage.util.{Vec2F, Vec2I}

class Game extends Scene {

  val sunOne = new Sun()
  sunOne.pClass = new SunI()
  sunOne.location = Vec2F(20, 20)
  val planetOne = new Planet()
  planetOne.pClass = new BarrenSmall()
  planetOne.parent = Some(sunOne)
  planetOne.distanceOut = 256
  planetOne.orbitalPeriod = 2000
  val moonOne = new Planet()
  moonOne.pClass = new MoonTiny()
  moonOne.parent = Some(planetOne)
  moonOne.distanceOut = 48
  moonOne.orbitalPeriod = 500
  val stationOne = new Station()
  stationOne.arch = new Outpost()
  stationOne.parent = Some(moonOne)
  stationOne.distanceOut = 6
  stationOne.orbitalPeriod = -50
  val cityOne = new City()
  cityOne.parent = planetOne
  cityOne.cityType = new CityI()
  cityOne.relativePosition = Vec2I(-5, -5)
  val player = new Player()
  def actors: List[Actor] =
    List(sunOne, planetOne, moonOne, stationOne, cityOne, player)

  def cameraLoc: Vec2F = Vec2F(
    player.location.x * screenUnit * zoom - (Geometry.ScreenWidth / 2),
    player.location.y * screenUnit * zoom - (Geometry.ScreenHeight / 2)
  )
  override def init(): InputAdapter = {
    actors.foreach(m => m.init(this))
    actors.foreach {
      case orbital: Orbital => orbital.orbitInit()
      case default          =>
    }
    player.location = stationOne.location.copy()
    player.arch = new Carc()
    player.engine = new MKI()
    new GameController(this)
  }

  override def update(delta: Float): Option[Scene] = {
    player.playerUpdate(this, delta)
    actors.foreach {
      case orbital: Orbital => orbital.orbitUpdate()
      case default          =>
    }
    actors.foreach(m => m.realUpdate(this, delta))
    None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
    batch.setTransformMatrix(
      new Matrix4().trn(-cameraLoc.x, -cameraLoc.y, 0).scl(zoom)
    )
    actors.foreach(m => m.draw(batch))
    batch.flush()
    batch.setTransformMatrix(new Matrix4())
    batch.draw(Salvage.Square, 0, 0, Geometry.ScreenWidth, screenUnit)
  }
}
