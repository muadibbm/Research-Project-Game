package com.domian.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.domian.core.Game;

public class GameActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("com/domian/resources");
    PlayN.run(new Game());
  }
}
