package org.eamonnh.salvage.util

case class Vec2(var x: Float, var y: Float) {
  def += (vec2: Vec2): Unit = {
    x += vec2.x
    y += vec2.y
  }
  def *= (vec2: Vec2): Unit = {
    x *= vec2.x
    y *= vec2.y
  }
  def *= (second: Float): Unit = {
    x *= second
    y *= second
  }
}
