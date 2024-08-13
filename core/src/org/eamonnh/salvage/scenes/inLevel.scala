package org.eamonnh.salvage.scenes

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import org.eamonn.salvage.{Scene, screenUnit}
import org.eamonn.salvage.scenes.LevelController
import org.eamonnh.salvage.player.{Carc, Player}
import org.eamonnh.salvage.util.Vec2

class inLevel extends Scene{

  val player = new Player()
  override def init(): InputAdapter = {
    player.location = Vec2(5, 5)
    player.archetype = Carc()

    new LevelController(this)
  }

  override def update(delta: Float): Option[Scene] = {
    None
  }

  override def render(batch: PolygonSpriteBatch): Unit = {
    player.draw(batch)
  }
}
