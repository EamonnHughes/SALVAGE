package org.eamonnh.salvage.actors.planets

import org.eamonnh.salvage.actors.{Actor, Orbital}
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.TextureWrapper

class Planet extends Actor with Orbital {

  override var parent: Option[Actor] = None
  override var distanceOut: Int = _
  override var orbitalPeriod: Float = _

  var pClass: PlanetClass = _
  override def size = pClass.size

  override def sprites: List[TextureWrapper] = List(pClass.sprite)

  override def init(game: Game): Unit = {}
  override def update(game: Game, delta: Float): Unit = {}
  override var pointAt: Boolean = false
}
