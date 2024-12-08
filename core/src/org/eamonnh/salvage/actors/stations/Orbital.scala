package org.eamonnh.salvage.actors.stations

import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage.util.Vec2F

trait Orbital extends Actor {
  var parent: Option[Actor]
  var distanceOut: Int
  var orbitalPeriod: Float
  var pointAt: Boolean

  def orbitUpdate(): Unit = {
    parent.foreach(par => {
      if(pointAt) rotation = Math.atan2((par.location.y - location.y), (par.location.x - location.x)).toFloat
      location = Vec2F(
        par.location.x + (distanceOut * Math.cos(((lifetime % orbitalPeriod) / orbitalPeriod) * Math.PI * 2)).toFloat,
        par.location.y + (distanceOut * Math.sin(((lifetime % orbitalPeriod) / orbitalPeriod) * Math.PI * 2)).toFloat
      )
    }
    )
  }
}
