package com.comp396.game.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.comp396.game.core.main;

public class mainHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("game/");
    PlayN.run(new main());
  }
}
