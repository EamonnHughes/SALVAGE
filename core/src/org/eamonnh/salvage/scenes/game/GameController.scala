package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter
import org.eamonnh.salvage.actors.ships.components.{MKI, MKII}
import org.eamonnh.salvage.actors.ships.{Carc, Corv}
import org.eamonnh.salvage.zoom

class GameController(game: Game) extends InputAdapter {
  override def touchDown(
      screenX: Int,
      screenY: Int,
      pointer: Int,
      button: Int
  ): Boolean = {
    true
  }

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {

    true
  }

  override def keyUp(keycode: Int): Boolean = {
    if (keycode == Keys.DOWN || keycode == Keys.S) GameTriggers.Back = false
    if (keycode == Keys.UP || keycode == Keys.W) GameTriggers.Forward = false
    if (keycode == Keys.LEFT || keycode == Keys.A) GameTriggers.Left = false
    if (keycode == Keys.RIGHT || keycode == Keys.D) GameTriggers.Right = false
    if (keycode == Keys.SHIFT_LEFT || keycode == Keys.SHIFT_RIGHT)
      GameTriggers.Shift = false
    game.player.playerKeyUps(keycode, game)
    true
  }

  override def keyDown(keycode: Int): Boolean = {
    if (keycode == Keys.DOWN || keycode == Keys.S) GameTriggers.Back = true
    if (keycode == Keys.UP || keycode == Keys.W) GameTriggers.Forward = true
    if (keycode == Keys.LEFT || keycode == Keys.A) GameTriggers.Left = true
    if (keycode == Keys.RIGHT || keycode == Keys.D) GameTriggers.Right = true
    if (keycode == Keys.SHIFT_LEFT || keycode == Keys.SHIFT_RIGHT)
      GameTriggers.Shift = true
    true
  }

  override def scrolled(amountX: Float, amountY: Float): Boolean = {
    zoom = (zoom + (amountY / 4)) max .025f min 40
    true
  }
}
