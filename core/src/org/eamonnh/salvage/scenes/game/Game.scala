package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonnh.salvage.Scene
import org.eamonnh.salvage.ships.components._
import org.eamonnh.salvage.player.Player
import org.eamonnh.salvage.ships.Carc
import org.eamonnh.salvage.util.Vec2F

class Game extends Scene{

  val player = new Player()
  override def init(): InputAdapter = {
    player.init()
    new GameController(this)
  }

  override def update(delta: Float): Option[Scene] = {
    player.playerUpdate(this, delta)
    player.realUpdate(this, delta)
    None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
    player.draw(batch)
  }
}
