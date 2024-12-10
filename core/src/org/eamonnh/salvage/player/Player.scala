package org.eamonnh.salvage.player

import com.badlogic.gdx.Input.Keys
import org.eamonnh.salvage.actors.ships.Ship
import org.eamonnh.salvage.actors.suns.Sun
import org.eamonnh.salvage.scenes.game.{Game, GameTriggers}

class Player extends Ship {

  def nearestSun(game: Game): Sun = game.actors.collect({ case sun: Sun => sun }).minBy(a => a.location.distanceFrom(location))

  def playerUpdate(game: Game, delta: Float): Unit = {
    if (GameTriggers.Forward) {
      if (GameTriggers.Shift) {
        movingFullSpeed = true
      } else {
        movingSlower = true
      }
    } else {
      movingSlower = false
      movingFullSpeed = false
    }
    if (GameTriggers.Right) {
      rotatingRight = true
      rotatingLeft = false
    } else if (GameTriggers.Left) {
      rotatingLeft = true
      rotatingRight = false
    } else {
      rotatingRight = false
      rotatingLeft = false
    }
  }
  def playerKeyUps(keycode: Int, game: Game): Unit = {
    if(keycode == Keys.L){
      if(anchorage.nonEmpty) anchorage = None else {
        TryToLand(game)
      }
    }
  }
}
