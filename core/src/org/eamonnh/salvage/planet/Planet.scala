package org.eamonnh.salvage.planet

import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.stations.{Outpost, StationArchetype}
import org.eamonnh.salvage.util.{TextureWrapper, Vec2F}

class Planet extends Actor {
  var pClass: PlanetClass = _
  override def size = pClass.size

  override def sprites: List[TextureWrapper] = List(pClass.sprite)

  override def init(): Unit = {
    pClass = new BarrenSmall()
    location = Vec2F(20, 20)
  }
  override def update(game: Game, delta: Float): Unit = {

  }
}