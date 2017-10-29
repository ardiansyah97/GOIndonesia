package com.mobcom.goindonesia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;

/**
 * Created by Ardiansyah on 28/10/2017.
 */

public class MapScreen implements Screen {

    private GOIndonesia game;
    private  Texture texture;
    private Viewport viewport;
    private Stage stage;

    public MapScreen(final GOIndonesia game) {
        this.game = game;
        viewport = new FitViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        texture = new Texture("background/map.png");

        Gdx.input.setInputProcessor(stage);

        Image keyJawa  = new Image(new Texture("background/lock_open.png"));
        keyJawa.setSize(35,40);
        keyJawa.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });

        Image imgBack = new Image(new Texture("background/back.png"));
        imgBack.setSize(80,80);
        imgBack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                return true;
            }
        });

        Table tableJawa = new Table();
        tableJawa.top();
        tableJawa.setFillParent(true);
        tableJawa.add(keyJawa).size(keyJawa.getWidth(), keyJawa.getHeight()).padLeft(-250);
        tableJawa.padTop(290);

        Table tableBack = new Table();
        tableBack.bottom().left();
        tableBack.add(imgBack).size(imgBack.getWidth(), imgBack.getHeight());
        tableBack.padLeft(15).padBottom(15);


        stage.addActor(tableJawa);
        stage.addActor(tableBack);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(texture, 0, 0, 1024, 512);
        game.batch.end();

        stage.draw();
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

    }
}
