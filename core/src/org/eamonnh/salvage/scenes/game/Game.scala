package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Matrix4
import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage._
import org.eamonnh.salvage.planet.{BarrenSmall, MoonTiny, Planet}
import org.eamonnh.salvage.player._
import org.eamonnh.salvage.stations._
import org.eamonnh.salvage.util.Vec2F

class Game extends Scene{

  val player = new Player()
  val planetOne = new Planet()
  planetOne.pClass = new BarrenSmall()
  planetOne.location = Vec2F(20, 20)
  val moonOne = new Planet()
  moonOne.pClass = new MoonTiny()
  moonOne.parent = Some(planetOne)
  moonOne.distanceOut = 25
  moonOne.orbitalPeriod = 500
  val stationOne = new Station()
  stationOne.arch = new Outpost()
  stationOne.parent = Some(moonOne)
  stationOne.distanceOut = 6
  stationOne.orbitalPeriod = 50

  def motiles: List[Actor] = List(planetOne, moonOne, stationOne, player)

  def cameraLoc: Vec2F = Vec2F(player.location.x * screenUnit - (Geometry.ScreenWidth/2), player.location.y * screenUnit - (Geometry.ScreenHeight/2))
  override def init(): InputAdapter = {
    motiles.foreach(m => m.init(this))
    new GameController(this)
  }

  override def update(delta: Float): Option[Scene] = {
    player.playerUpdate(this, delta)
    motiles.foreach {
      case orbital: Orbital => orbital.orbitUpdate()
      case default => {

      }
    }
    motiles.foreach(m => m.realUpdate(this, delta))
    None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
    batch.setTransformMatrix(
      new Matrix4().trn(-cameraLoc.x, -cameraLoc.y, 0).scl(zoom)
    )
    motiles.foreach(m => m.draw(batch))
    batch.flush()
    batch.setTransformMatrix(new Matrix4())
    batch.draw(Salvage.Square, 0, 0, Geometry.ScreenWidth, screenUnit)
  }
}
