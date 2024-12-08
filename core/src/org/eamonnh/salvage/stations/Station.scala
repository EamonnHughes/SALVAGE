package org.eamonnh.salvage.stations
import org.eamonnh.salvage.actors._
import org.eamonnh.salvage.scenes.game._
import org.eamonnh.salvage.ships.{ShipArchetype, Carc}
import org.eamonnh.salvage.ships.components._
import org.eamonnh.salvage.util._

class Station extends Actor {
  var arch: StationArchetype = _
  override def size = arch.size

  override def sprites: List[TextureWrapper] = List(arch.sprite)

  override def init(): Unit = {
    arch = new Outpost()
    location = Vec2F(5, 5)
  }
  override def update(game: Game, delta: Float): Unit = {

  }
}