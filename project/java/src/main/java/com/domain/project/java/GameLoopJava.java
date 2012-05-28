package com.domain.project.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.domain.project.core.GameLoop;

public class GameLoopJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("com/domain/project/resources");
    PlayN.run(new GameLoop());
  }
}
