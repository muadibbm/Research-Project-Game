package com.project.game.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.project.game.core.Main;

public class MainActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("com/project/game/resources");
    PlayN.run(new Main());
  }
}
