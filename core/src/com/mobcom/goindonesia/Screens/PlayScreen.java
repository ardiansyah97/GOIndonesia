package com.mobcom.goindonesia.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.Scenes.Controller;
import com.mobcom.goindonesia.Scenes.Hud;

/**
 * Created by Ardiansyah on 02/10/2017.
 */

public class PlayScreen implements Screen {
    private GOIndonesia game;
    private Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private Controller controller;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    public PlayScreen(GOIndonesia game){
        this.game = game;
        texture = new Texture("badlogic.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, gameCam);
        hud = new Hud(game.batch);
        controller = new Controller(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(controller.isRightPressed())
            gameCam.position.x += 100 * dt;
        else if (controller.isLeftPressed())
            gameCam.position.x -= 100 * dt;
        else if (controller.isDownPressed())
            gameCam.position.y -= 100 * dt;
        else if (controller.isUpPressed())
            gameCam.position.y += 100 * dt;

    }

    public void update(float dt){
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }




    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        controller.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        controller.resize(width, height);
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

    }
}
