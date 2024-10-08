package org.eamonn.salvage

import com.badlogic.gdx.backends.lwjgl3.{
  Lwjgl3Application,
  Lwjgl3ApplicationConfiguration
}

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
object DesktopLauncher extends App {
  val config = new Lwjgl3ApplicationConfiguration
  config.setForegroundFPS(60)
  config.setWindowedMode(1500, 1000)
  new Lwjgl3Application(new Salvage, config)
}
