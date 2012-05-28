package com.domain.project.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.domain.project.core.GameLoop;

public class GameLoopHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("project/");
    PlayN.run(new GameLoop());
  }
}
