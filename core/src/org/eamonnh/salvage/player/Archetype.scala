package org.eamonnh.salvage.player

import org.eamonn.salvage.Salvage.garbage
import org.eamonn.salvage.util.TextureWrapper

trait Archetype {
  var sprite: TextureWrapper
}

case class Carc() extends Archetype {

  override var sprite: TextureWrapper = TextureWrapper.load("Carcharodon.png")
}