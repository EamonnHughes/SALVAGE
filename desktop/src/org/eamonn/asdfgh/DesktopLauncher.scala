package org.eamonnh.salvage

import com.badlogic.gdx.backends.lwjgl3.{
  Lwjgl3Application,
  Lwjgl3ApplicationConfiguration
}

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
object DesktopLauncher extends App {
  val config = new Lwjgl3ApplicationConfiguration
  config.setForegroundFPS(60)
  config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode)
  new Lwjgl3Application(new Salvage, config)
}
