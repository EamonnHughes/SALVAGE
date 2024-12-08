package org.eamonnh.salvage.stations
import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.scenes.game._
import org.eamonnh.salvage.ships.{ShipArchetype, Carc}
import org.eamonnh.salvage.ships.components._
import org.eamonnh.salvage.util._

class Station extends Actor {
  var arch: StationArchetype = _
  var parent: Option[Actor] = None
  var distanceOut: Int = 25
  var orbitalPeriod: Float = 500
  override def size = arch.size

  override def sprites: List[TextureWrapper] = List(arch.sprite)

  override def init(game: Game): Unit = {
    arch = new Outpost()
    parent = Some(game.planetOne)
    location = Vec2F(5, 5)
    parent.foreach(par => {
      location = Vec2F(par.location.x, par.location.y - distanceOut)
    })
    rotation = Math.PI.toFloat
  }
  override def update(game: Game, delta: Float): Unit = {
    parent.foreach(par => {
      rotation = Math.atan2((par.location.y - location.y), (par.location.x - location.x)).toFloat
      location = Vec2F(
        par.location.x + (distanceOut * Math.cos(((lifetime % orbitalPeriod) / orbitalPeriod) * Math.PI * 2)).toFloat,
        par.location.y + (distanceOut * Math.sin(((lifetime % orbitalPeriod) / orbitalPeriod) * Math.PI * 2)).toFloat
      )
    })
  }
}