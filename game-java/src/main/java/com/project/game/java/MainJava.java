package com.project.game.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.project.game.core.Main;

public class MainJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/project/game/resources");
    PlayN.run(new Main());
  }
}
