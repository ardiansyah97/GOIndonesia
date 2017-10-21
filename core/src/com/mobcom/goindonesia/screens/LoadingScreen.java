package com.mobcom.goindonesia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
        game.assetManager.load("logo.png", Texture.class);

    }

    @Override
    public void show() {
        System.out.println("LOADING");
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, game.assetManager.getProgress(), .1f);
        //System.out.println(progress + "" + game.assets.getProgress());
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
        /*shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, game.cam.viewportHeight / 2 - 8, game.cam.viewportWidth - 64, game.cam.viewportHeight / 2 + 8);*/
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, game.cam.viewportHeight / 2 - 8, game.cam.viewportWidth - 64, 16); // 210

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, game.cam.viewportHeight / 2 - 8, progress * (game.cam.viewportWidth - 64), 16);  //210
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
