package org.eamonnh.salvage.actors.stations

import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.scenes.game._
import org.eamonnh.salvage.util._

class Station extends Actor with Orbital {
  var arch: StationArchetype = _

  override var parent: Option[Actor] = None
  override var distanceOut: Int = _
  override var orbitalPeriod: Float = _
  override var pointAt: Boolean = true

  override def size = arch.size

  override def sprites: List[TextureWrapper] = List(arch.sprite)

  override def init(game: Game): Unit = {
    parent.foreach(par => {
      location = Vec2F(par.location.x, par.location.y - distanceOut)
    })
  }
  override def update(game: Game, delta: Float): Unit = {}
}
