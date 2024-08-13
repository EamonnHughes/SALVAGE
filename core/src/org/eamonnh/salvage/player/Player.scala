package org.eamonnh.salvage.player

import org.eamonnh.salvage.Motile
import org.eamonnh.salvage.util.Vec2

class Player extends Motile{

  override var location: Vec2 = _
  var archetype: Archetype = _

  override def sprite = archetype.sprite

  override var size: Vec2 = Vec2(4, 4)
}
