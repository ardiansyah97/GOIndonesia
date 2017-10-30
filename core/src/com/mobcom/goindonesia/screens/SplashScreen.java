package com.mobcom.goindonesia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mobcom.goindonesia.GOIndonesia;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Ardiansyah on 21/10/2017.
 */

public class SplashScreen implements Screen {
    private GOIndonesia game;
    private Stage stage;
    private Image splashImgStudio, splashImgGame;

    public SplashScreen(final GOIndonesia game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, game.cam));
        Gdx.input.setInputProcessor(stage);

        GOIndonesia.assetManager.get("audio/s_splash.wav", Sound.class).play();

        Texture splashTexStudio = game.assetManager.get("logo_studio.png", Texture.class);
        Texture splashTexGame = game.assetManager.get("logo_game.png", Texture.class);

        splashImgGame = new Image(splashTexGame);
        splashImgGame.setOrigin(splashImgGame.getWidth() / 2, splashImgGame.getHeight() / 2);

        splashImgStudio = new Image(splashTexStudio);
        splashImgStudio.setOrigin(splashImgStudio.getWidth() / 2, splashImgStudio.getHeight() / 2);

        splashImgGame.setVisible(false);
        stage.addActor(splashImgGame);
        stage.addActor(splashImgStudio);
    }

    @Override
    public void show(){
        System.out.println("Splash Screen");

        splashImgStudio.setPosition(stage.getWidth() / GOIndonesia.PPM , stage.getHeight() / GOIndonesia.PPM );
        splashImgStudio.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(1f, 1f, 2.5f, Interpolation.pow5),
                        moveTo(stage.getWidth() / GOIndonesia.PPM, stage.getHeight() / GOIndonesia.PPM , 2f, Interpolation.swing)),
                delay(1f), fadeOut(1f)));

        float delay1 = 5;

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                splashImgGame.setVisible(true);
                //splashImgGame.addAction(sequence(alpha(1), scaleTo(.1f, .1f), fadeOut(2f)));
            }
        }, delay1);

        splashImgGame.setPosition(stage.getWidth() / GOIndonesia.PPM + 330, stage.getHeight() / GOIndonesia.PPM + 108);
        //splashImgGame.setPosition(stage.getWidth() / GOIndonesia.PPM , stage.getHeight() / GOIndonesia.PPM );
        splashImgGame.addAction(sequence(delay(5f), alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(1f, 1f, 2.5f, Interpolation.pow5),
                        moveTo(stage.getWidth() / GOIndonesia.PPM + 330, stage.getHeight() / GOIndonesia.PPM + 108, 2f, Interpolation.swing)),
                delay(1f), fadeOut(1.25f)));

        float delay = 10; // seconds

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                game.setScreen(new MainMenuScreen(game));
            }
        }, delay);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.draw();
    }

    private void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        System.out.println("Pause");
    }

    @Override
    public void resume() {
        System.out.println("Resume");
    }

    @Override
    public void hide() {
        System.out.println("Hide Splashscreen");
    }

    @Override
    public void dispose() {
        System.out.println("Dispose");
        stage.dispose();
    }


}

