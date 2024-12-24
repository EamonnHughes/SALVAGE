package org.eamonnh.salvage.actors.planets.cities

import org.eamonnh.salvage.actors.{Actor, Anchorage}
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.{TextureWrapper, Vec2F, Vec2I}

class City extends Actor with Anchorage {

  var parent: Actor = _
  var cityType: CityType = _
  var relativePosition: Vec2F = _
  override def size: Vec2I = cityType.size
  override def sprites: List[TextureWrapper] = List(cityType.sprite)
  override def update(game: Game, delta: Float): Unit = {
    location = Vec2F(
      parent.location.x + relativePosition.x,
      parent.location.y + relativePosition.y
    )
  }

  override def init(game: Game): Unit = {}
}
