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
import org.eamonnh.salvage.actors.ships.components.{MKI, MKII, Tokamak}
import org.eamonnh.salvage.actors.stations.{Outpost, Station}
import org.eamonnh.salvage.actors.suns.{Sun, SunI}
import org.eamonnh.salvage.player._
import org.eamonnh.salvage.util.{Vec2F, Vec2I}

class Game extends Scene {

  val sunOne = new Sun()
  sunOne.pClass = new SunI()
  sunOne.location = Vec2F(20, 20)
  sunOne.name = "Sol Secundus"
  val planetOne = new Planet()
  planetOne.pClass = new BarrenSmall()
  planetOne.parent = Some(sunOne)
  planetOne.distanceOut = 256
  planetOne.orbitalPeriod = 2000
  planetOne.name = "New Terra"
  val moonOne = new Planet()
  moonOne.pClass = new MoonTiny()
  moonOne.parent = Some(planetOne)
  moonOne.distanceOut = 48
  moonOne.orbitalPeriod = 500
  moonOne.name = "Qamr"
  val stationOne = new Station()
  stationOne.arch = new Outpost()
  stationOne.parent = Some(moonOne)
  stationOne.distanceOut = 6
  stationOne.orbitalPeriod = -50
  stationOne.name = "Autonomous Data Unit I"
  val cityOne = new City()
  cityOne.parent = planetOne
  cityOne.cityType = new CityI()
  cityOne.relativePosition = Vec2I(5, 5)
  cityOne.name = "Riyadh A-thani"
  val shipOne = new Ship
  shipOne.arch = new Corv
  shipOne.engine = new MKII
  shipOne.location = Vec2F(0, 0)
  val player = new Player()
  player.name = "Tzadkiel"
  var suns: List[Sun] = List(sunOne)
  var planets: List[Planet] = List(planetOne, moonOne)
  var stations: List[Station] = List(stationOne)
  var cities: List[City] = List(cityOne)
  var ships: List[Ship] = List(shipOne, player)
  def actors: List[Actor] = suns ::: planets ::: stations ::: cities ::: ships

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
    player.arch = new Vasa()
    player.engine = new Tokamak()
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
