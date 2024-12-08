package org.eamonnh.salvage.util

case class Vec2F(var x: Float, var y: Float) {
  def +=(vec2: Vec2F): Unit = {
    x += vec2.x
    y += vec2.y
  }
  def *=(vec2: Vec2F): Unit = {
    x *= vec2.x
    y *= vec2.y
  }
  def +=(vec2: Vec2I): Unit = {
    x += vec2.asFloat.x
    y += vec2.asFloat.y
  }
  def *=(vec2: Vec2I): Unit = {
    x *= vec2.asFloat.x
    y *= vec2.asFloat.y
  }
  def *=(second: Float): Unit = {
    x *= second
    y *= second
  }

  def asInt: Vec2I = new Vec2I(x.round, y.round)
}

case class Vec2I(var x: Int, var y: Int) {
  def +=(vec2: Vec2F): Unit = {
    x += vec2.asInt.x
    y += vec2.asInt.y
  }
  def *=(vec2: Vec2F): Unit = {
    x *= vec2.asInt.x
    y *= vec2.asInt.y
  }
  def +=(vec2: Vec2I): Unit = {
    x += vec2.x
    y += vec2.y
  }
  def *=(vec2: Vec2I): Unit = {
    x *= vec2.x
    y *= vec2.y
  }
  def *=(second: Int): Unit = {
    x *= second
    y *= second
  }
  def asFloat: Vec2F = new Vec2F(x.toFloat, y.toFloat)
}
