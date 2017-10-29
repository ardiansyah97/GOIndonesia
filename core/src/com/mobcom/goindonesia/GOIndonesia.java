package com.mobcom.goindonesia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mobcom.goindonesia.scenes.Controller;
import com.mobcom.goindonesia.screens.LoadingScreen;

public class GOIndonesia extends Game {
	//Virtual Screen size and Box2D Scale(Pixels Per Meter)
	public static final int V_WIDTH = 1024;
	public static final int V_HEIGHT = 512;
	public static final float PPM = 100;

	//Box2D Collision Bits
	public static final short GROUND_BIT = 1;
	public static final short GARUDA_BIT = 2;
	public static final short ENEMY_BIT = 4;
	public static final short PROJECTILE_BIT = 8;


	public static SpriteBatch batch;
	public OrthographicCamera cam;
	public static AssetManager assetManager;
	public static Preferences pref;

	public Controller controller;

	@Override
	public void create () {
		batch = new SpriteBatch();
		controller = new Controller(batch);

		assetManager = new AssetManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		pref = Gdx.app.getPreferences("GOIndonesia");
		if(!pref.contains("coins")){
			pref.putInteger("coins", 0);
		}

		setScreen(new LoadingScreen(this));
	}

	public static void setCoins(int val){
		pref.putInteger("coins", val);
		pref.flush();
	}

	public static int getCoins(){
		return pref.getInteger("coins");
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
