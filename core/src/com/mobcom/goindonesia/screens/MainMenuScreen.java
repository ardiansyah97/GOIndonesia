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
 * Created by Ardiansyah on 26/10/2017.
 */

public class MainMenuScreen implements Screen {

    private Stage stage;
    private Viewport viewport;

    private GOIndonesia game;
    private Texture texture;

    public MainMenuScreen(final GOIndonesia game){
        this.game = game;

        viewport = new FitViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        texture = new Texture("background/bgmainmenu.png");

        Gdx.input.setInputProcessor(stage);

		Image imgSetting  = new Image(new Texture("background/setting_button.png"));
		imgSetting.setSize(60,60);
		
        Image imgPlay = new Image(new Texture("background/play_button.png"));
        imgPlay.setSize(80,80);
        imgPlay.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });


        Image imgShop = new Image(new Texture("background/shop_button.png"));
        imgShop.setSize(80,80);

        Image imgAbout = new Image(new Texture("background/about_button.png"));
        imgAbout.setSize(80,80);

        Image imgExit= new Image(new Texture("background/exit_button.png"));
        imgExit.setSize(80,80);

		Table topRight = new Table();
		topRight.right().top();
		topRight.setFillParent(true);
		topRight.add(imgSetting).size(imgSetting.getWidth(), imgSetting.getHeight());
		topRight.padRight(15).padTop(15);
		
        Table botLeft= new Table();
        botLeft.left().bottom();
        botLeft.setFillParent(true);
        botLeft.add(imgPlay).size(imgPlay.getWidth(), imgPlay.getHeight()).padRight(15);
        botLeft.add(imgShop).size(imgShop.getWidth(), imgShop.getHeight()).padRight(15);
        botLeft.add(imgAbout).size(imgAbout.getWidth(), imgAbout.getHeight()).padRight(15);
        botLeft.padLeft(15).padBottom(15);

        Table botRight = new Table();
        botRight.bottom().right();
        botRight.setFillParent(true);
        botRight.add(imgExit).size(imgExit.getWidth(), imgExit.getHeight());
        botRight.padRight(15).padBottom(15);

		stage.addActor(topRight);
        stage.addActor(botLeft);
        stage.addActor(botRight);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 0, 0, 1);
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
