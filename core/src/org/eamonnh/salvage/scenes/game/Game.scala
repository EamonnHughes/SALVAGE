package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Matrix4
import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage._
import org.eamonnh.salvage.planet.Planet
import org.eamonnh.salvage.player._
import org.eamonnh.salvage.stations._
import org.eamonnh.salvage.util.Vec2F

class Game extends Scene{

  val player = new Player()
  val stationOne = new Station()
  val planetOne = new Planet()

  def motiles: List[Actor] = List(planetOne, stationOne, player)

  def cameraLoc: Vec2F = Vec2F(player.location.x * screenUnit - (Geometry.ScreenWidth/2), player.location.y * screenUnit - (Geometry.ScreenHeight/2))
  override def init(): InputAdapter = {
    motiles.foreach(m => m.init())
    new GameController(this)
  }

  override def update(delta: Float): Option[Scene] = {
    player.playerUpdate(this, delta)
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
