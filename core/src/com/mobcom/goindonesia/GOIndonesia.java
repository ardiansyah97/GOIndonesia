package com.mobcom.goindonesia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mobcom.goindonesia.scenes.Controller;
import com.mobcom.goindonesia.screens.LoadingScreen;

public class GOIndonesia extends Game {
	public static SpriteBatch batch;
	public static final int V_WIDTH = 1024;
	public static final int V_HEIGHT = 512;
	public static final float PPM = 100;
	public OrthographicCamera cam;
	public static AssetManager assetManager;

	public Controller controller;

	@Override
	public void create () {
		batch = new SpriteBatch();
		controller = new Controller(batch);

		assetManager = new AssetManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		assetManager.dispose();
		this.getScreen().dispose();
	}

	public static AssetManager getAssetManager(){
		return assetManager;
	}
}
