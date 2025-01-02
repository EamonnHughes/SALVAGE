package org.eamonnh.salvage.actors

import org.eamonnh.salvage.actors.ships.Ship

trait Anchorage extends Actor {
  var canLand: Ship => Boolean
}
