package com.mobcom.goindonesia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mobcom.goindonesia.GOIndonesia;

/**
 * Created by Ardiansyah on 21/10/2017.
 */

public class LoadingScreen implements Screen {
    public final GOIndonesia game;

    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(final GOIndonesia game){
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;
        queueAssets();
    }

    private void queueAssets(){
        game.assetManager.load("logo/logo_studio.png", Texture.class);
        game.assetManager.load("logo/logo_game.png", Texture.class);
        game.assetManager.load("logo/logo_game4.png", Texture.class);
        game.assetManager.load("logo/logo_game5.png", Texture.class);

        game.assetManager.load("atlas/atlas-1.pack", TextureAtlas.class);
        game.assetManager.load("atlas/atlas-2.pack", TextureAtlas.class);
        game.assetManager.load("atlas/hud.pack", TextureAtlas.class);

        game.assetManager.load("font/my-font.fnt", BitmapFont.class);

        game.assetManager.load("audio/coin.wav", Sound.class);
        game.assetManager.load("audio/s_coin.wav", Sound.class);
        game.assetManager.load("audio/s_pistol.wav", Sound.class);
        game.assetManager.load("audio/s_splash.wav", Sound.class);
        game.assetManager.load("audio/s_death.mp3", Sound.class);
        game.assetManager.load("audio/s_death1.wav", Sound.class);
        game.assetManager.load("audio/s_death2.wav", Sound.class);
        game.assetManager.load("audio/s_death3.wav", Sound.class);
        game.assetManager.load("audio/s_button.wav", Sound.class);
        game.assetManager.load("audio/s_button2.wav", Sound.class);

        game.assetManager.load("audio/s_candi.wav", Music.class);
        game.assetManager.load("audio/s_menu.wav", Music.class);
        game.assetManager.load("audio/s_jawa.wav", Music.class);
        game.assetManager.load("audio/s_menu.wav", Music.class);
        game.assetManager.load("audio/s_map.wav", Music.class);
        game.assetManager.load("audio/music.ogg", Music.class);
    }

    @Override
    public void show() {
        System.out.println("Loading Sreen");
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, game.assetManager.getProgress(), .1f);

        if(game.assetManager.update()  && progress <= game.assetManager.getProgress() - .001f){
            game.setScreen(new SplashScreen(game)); // muncul splash screen
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(256, game.cam.viewportHeight / 5 - 8, game.cam.viewportWidth / 2 - 64, 16); // 210
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(256, game.cam.viewportHeight / 5 - 8, progress * (game.cam.viewportWidth / 2 - 64), 16);  //210
        shapeRenderer.end();


        game.batch.begin();
        //game.font.draw(game.batch, "Screen: Loading", 20, 20);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

}
