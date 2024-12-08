package org.eamonnh.salvage.actors.suns

import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.TextureWrapper

class Sun extends Actor {

  var pClass: SunClass = _
  override def size = pClass.size

  override def sprites: List[TextureWrapper] = List(pClass.sprite)

  override def init(game: Game): Unit = {}
  override def update(game: Game, delta: Float): Unit = {}
}
