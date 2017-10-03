package com.mobcom.goindonesia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mobcom.goindonesia.Scenes.Controller;
import com.mobcom.goindonesia.Screens.PlayScreen;

public class GOIndonesia extends Game {
	public static SpriteBatch batch;
	public static int V_WIDTH = 400;
	public static int V_HEIGHT = 208;
	public static final float PPM = 100;

	Controller controller;


	@Override
	public void create () {
		batch = new SpriteBatch();
		controller = new Controller(batch);
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
