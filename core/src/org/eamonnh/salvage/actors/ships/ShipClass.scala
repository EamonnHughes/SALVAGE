package org.eamonnh.salvage.actors.ships

import org.eamonnh.salvage.util._

abstract class ShipClass {
  var size: Vec2I
}

class Fighter extends ShipClass {

  override var size: Vec2I = Vec2I(2, 2)

}