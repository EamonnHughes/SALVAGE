package org.eamonnh.salvage.ships

import org.eamonnh.salvage.actors.Motile
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.ships.components.{Engine, MKI}
import org.eamonnh.salvage.util.Vec2F

abstract class Ship extends Motile {
  var arch: Archetype = _
  var engine: Engine = _
  var movingForward: Boolean = false
  var braking: Boolean = false

  override def init(): Unit = {
    location = Vec2F(5, 5)
    arch = new Carc()
    engine = new MKI()
    size = arch.shipClass.size
  }
  override def update(game: Game, delta: Float): Unit = {
    if(movingForward) {

    } else if(braking) {

    }
  }
}
