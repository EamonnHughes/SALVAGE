package org.eamonn.salvage
package scenes

import com.badlogic.gdx.InputAdapter
import org.eamonnh.salvage.scenes.inLevel

class LevelController(level: inLevel) extends InputAdapter {
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

  override def keyDown(keycode: Int): Boolean = {

    true
  }
}
