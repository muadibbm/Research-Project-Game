package com.domian.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.domian.core.Game;

public class GameJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/domian/resources");
    PlayN.run(new Game());
  }
}
