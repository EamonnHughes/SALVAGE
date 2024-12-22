package org.eamonnh.salvage.scenes.start

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter

class StartControl(start: Start) extends InputAdapter {
  override def touchDown(
      screenX: Int,
      screenY: Int,
      pointer: Int,
      button: Int
  ): Boolean = {
    true
  }

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    start.menu.foreach(_.items.foreach(_.update(start)))
    true
  }

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {

    true
  }

  override def keyDown(keycode: Int): Boolean = {
    if (keycode == Keys.SPACE) start.ready = true
    true
  }
}
