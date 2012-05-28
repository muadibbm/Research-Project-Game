package com.domain.project.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.domain.project.core.GameLoop;

public class GameLoopActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("com/domain/project/resources");
    PlayN.run(new GameLoop());
  }
}
