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
    if(keycode == Keys.DOWN || keycode == Keys.S) GameTriggers.playerMovingBack = false
    if(keycode == Keys.UP || keycode == Keys.W) GameTriggers.playerMovingForward = false
    true
  }

  override def keyDown(keycode: Int): Boolean = {
    if(keycode == Keys.DOWN || keycode == Keys.S) {
      GameTriggers.playerMovingBack = true
      GameTriggers.playerMovingForward = false
    }
    if(keycode == Keys.UP || keycode == Keys.W) {
      GameTriggers.playerMovingForward = true
      GameTriggers.playerMovingBack = false
    }
    true
  }
}
