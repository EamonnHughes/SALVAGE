package org.eamonnh.salvage.actors
import org.eamonnh.salvage.scenes.game.Game

abstract class Ship extends Motile {
  var arch: Archetype = _
  var engine: Engine = _
  var movingForward: Boolean = false
  var braking: Boolean = false

  override def update(game: Game, delta: Float): Unit = {
    if(movingForward) {

    } else if(braking) {

    }
  }
}
