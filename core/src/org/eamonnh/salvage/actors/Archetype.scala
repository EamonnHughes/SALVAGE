package org.eamonnh.salvage.actors

import org.eamonnh.salvage.Salvage.garbage
import org.eamonnh.salvage.util.TextureWrapper

trait Archetype {
  var sprite: TextureWrapper
}

case class Carc() extends Archetype {

  override var sprite: TextureWrapper = TextureWrapper.load("Carcharodon.png")
}