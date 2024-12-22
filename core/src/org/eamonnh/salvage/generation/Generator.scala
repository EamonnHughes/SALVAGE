package org.eamonnh.salvage.generation

import org.eamonnh.salvage.actors.Actor
import org.eamonnh.salvage.actors.planets.cities.{City, CityI}
import org.eamonnh.salvage.actors.planets.{BarrenSmall, MoonTiny, Planet}
import org.eamonnh.salvage.actors.ships.behavior.{Convoy, Trader}
import org.eamonnh.salvage.actors.ships.components.{MKI, MKII, Tokamak}
import org.eamonnh.salvage.actors.ships.{Carc, Corv, Destroyer, Fighter, Ship, Vasa}
import org.eamonnh.salvage.actors.stations.{Outpost, Station}
import org.eamonnh.salvage.actors.suns.{Sun, SunI}
import org.eamonnh.salvage.crew.Officer
import org.eamonnh.salvage.player.Player
import org.eamonnh.salvage.scenes.game.Game
import org.eamonnh.salvage.util.{Vec2F, Vec2I}

object Generator {
  var game: Game = _

  def generatePlayer(): Unit = {
    var player = new Player
    player.name = "Tzadkiel"
    player.location = game.stations.head.location.copy()
    player.arch = new Vasa
    player.engine = new Tokamak
    player.captain = new Officer
    player.captain.name = "Severian"
    player.captain.credits = 100
    game.ships = player :: game.ships
  }
  def generateTrader(): Unit = {
    var ship = new Ship
    ship.name = "Trader " + (Math.random() * 100).toInt
    ship.location = game
      .anchorages((Math.random() * game.anchorages.length).toInt)
      .location
      .copy()
    if((Math.random() * 2).toInt == 1) {
      ship.arch = new Corv
    } else if((Math.random() * 2).toInt == 1) {
      ship.arch = new Carc
    } else ship.arch = new Vasa
    ship.arch.shipClass match {
      case _: Fighter =>
        if ((Math.random() * 2).toInt == 1) ship.engine = new MKI else ship.engine = new MKII
      case _: Destroyer =>
        ship.engine = new Tokamak
      case _ =>
    }
    ship.behavior = Some(new Trader)
    ship.captain = new Officer
    ship.captain.name = "Unnamed Trader"
    ship.captain.credits = 1000
    game.ships = ship :: game.ships
    if(ship.arch.shipClass.isInstanceOf[Destroyer]) {
      val escortOne = new Ship
      escortOne.arch = new Corv
      escortOne.engine = new MKII
      escortOne.location = Vec2F(ship.location.x, ship.location.y + 5)
      escortOne.behavior = Some(new Convoy(ship))
      escortOne.captain = new Officer()
      escortOne.captain.name = "Unnamed Pilot"
      escortOne.captain.credits = 10
      escortOne.name = "Escort of " + ship.name
      game.ships = escortOne :: game.ships
    }
  }

  def generateSolarSystem(location: Vec2F): Unit = {
    val sun = makeSun(location)
    val planetOne = makePlanet(sun)
    val moonOne = makeMoon(planetOne)
    val stationOne = makeStation(moonOne)
    val cityOne = makeCity(planetOne)
    game.suns = sun :: game.suns
    game.planets = planetOne :: moonOne :: game.planets
    game.stations = stationOne :: game.stations
    game.cities = cityOne :: game.cities
  }

  def makeSun(location: Vec2F): Sun = {
    val sunOne = new Sun
    sunOne.pClass = new SunI
    sunOne.location = location
    sunOne.name = "Sol Secundus"
    sunOne
  }

  def makePlanet(parent: Actor): Planet = {
    val planetOne = new Planet
    planetOne.pClass = new BarrenSmall
    planetOne.parent = Some(parent)
    planetOne.distanceOut = 256
    planetOne.orbitalPeriod = 2000
    planetOne.name = "New Terra"
    var rots = (Math.random() * 100).toInt
    for (i <- 0 until rots) {
      planetOne.lifetime += rots
      planetOne.orbitUpdate()
    }
    planetOne
  }

  def makeMoon(parent: Actor): Planet = {
    val moonOne = new Planet
    moonOne.pClass = new MoonTiny
    moonOne.parent = Some(parent)
    moonOne.distanceOut = 48
    moonOne.orbitalPeriod = 500
    moonOne.name = "Qamr"
    var rots = (Math.random() * 100).toInt
    for (i <- 0 until rots) {
      moonOne.lifetime += rots
      moonOne.orbitUpdate()
    }
    moonOne
  }

  def makeStation(parent: Actor): Station = {
    val stationOne = new Station
    stationOne.arch = new Outpost
    stationOne.parent = Some(parent)
    stationOne.distanceOut = 6
    stationOne.orbitalPeriod = -50
    stationOne.name = "Autonomous Unit " + (Math.random() * 100).toInt
    var rots = (Math.random() * 100).toInt
    for (i <- 0 until rots) {
      stationOne.lifetime += rots
      stationOne.orbitUpdate()
    }
    stationOne
  }

  def makeCity(actor: Actor): City = {
    val cityOne = new City
    cityOne.parent = actor
    cityOne.cityType = new CityI
    cityOne.relativePosition = Vec2I(5, 5)
    cityOne.name = "Riyadh A-thani"
    return cityOne
  }

}
