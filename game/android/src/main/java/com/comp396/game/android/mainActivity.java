package com.comp396.game.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.comp396.game.core.main;

public class mainActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("com/comp396/game/resources");
    PlayN.run(new main());
  }
}
