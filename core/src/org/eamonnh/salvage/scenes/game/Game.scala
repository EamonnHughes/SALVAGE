package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.Scene
import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.player.Player
import org.eamonnh.salvage.util.Vec2

class Game extends Scene{

  val player = new Player()
  override def init(): InputAdapter = {
    player.location = Vec2(5, 5)
    player.size = Vec2(2, 2)
    player.arch = Carc()
    player.engine = MKI()
    player.rotation = 0
    new GameController(this)
  }

  override def update(delta: Float): Option[Scene] = {
    player.realUpdate(this, delta)
    None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
    player.draw(batch)
  }
}
