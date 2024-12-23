package org.eamonnh.salvage

import com.badlogic.gdx.Gdx
import org.eamonnh.salvage.util.Vec2F

object Geometry {

  def ScreenWidth: Float = Gdx.graphics.getWidth.toFloat
  def ScreenHeight: Float = Gdx.graphics.getHeight.toFloat

  def MenuStart: Vec2F = {
    Vec2F((ScreenWidth - ScreenHeight) / 2, 0)
  }
  def MenuEnd: Vec2F = {
    Vec2F(ScreenWidth - MenuStart.x, ScreenHeight)
  }

}
