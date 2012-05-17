package com.comp396.game.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.comp396.game.core.main;

public class mainJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/comp396/game/resources");
    PlayN.run(new main());
  }
}
