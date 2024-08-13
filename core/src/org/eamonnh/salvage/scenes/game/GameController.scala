package org.eamonnh.salvage.scenes.game

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter

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
    if(keycode == Keys.DOWN || keycode == Keys.S) GameTriggers.playerMovingDown = false
    if(keycode == Keys.UP || keycode == Keys.W) GameTriggers.playerMovingUp = false
    true
  }

  override def keyDown(keycode: Int): Boolean = {
    if(keycode == Keys.DOWN || keycode == Keys.S) {
      GameTriggers.playerMovingDown = true
      GameTriggers.playerMovingUp = false
    }
    if(keycode == Keys.UP || keycode == Keys.W) {
      GameTriggers.playerMovingUp = true
      GameTriggers.playerMovingDown = false
    }
    true
  }
}
