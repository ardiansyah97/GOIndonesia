package com.mobcom.goindonesia.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;

/**
 * Created by Ardiansyah on 03/10/2017.
 */

public class Controller {

    Viewport viewport;
    Stage stage;
    boolean leftPressed, rightPressed, upPressed, spacePressed;
    OrthographicCamera cam;

    public Controller(SpriteBatch sb){
        cam = new OrthographicCamera();
        viewport = new FitViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        
        float sizeWidth = 70;
        float sizeHeight = 70;
        
        stage.addListener(new InputListener(){

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch(keycode){
                    case Input.Keys.LEFT:
                        leftPressed = true;
                        break;
                    case Input.Keys.RIGHT:
                        rightPressed = true;
                        break;
                    case Input.Keys.SPACE:
                        spacePressed = true;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch(keycode){
                    case Input.Keys.LEFT:
                        leftPressed = false;
                        break;
                    case Input.Keys.RIGHT:
                        rightPressed = false;
                        break;
                    case Input.Keys.UP:
                        upPressed = false;
                        break;
                    case Input.Keys.SPACE:
                        spacePressed = false;
                        break;
                }
                return true;
            }


        });

        Gdx.input.setInputProcessor(stage);

        Image rightImg = new Image(new Texture("controller/kanan.png"));
        rightImg.setSize(sizeWidth, sizeHeight);
        rightImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        Image leftImg = new Image(new Texture("controller/kiri.png"));
        leftImg.setSize(sizeWidth, sizeHeight);
        leftImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        Image jumpImg = new Image(new Texture("controller/jump.png"));
        jumpImg.setSize(sizeWidth, sizeHeight);
        jumpImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                leftPressed = false;
            }
        });

        Image fireImg = new Image(new Texture("controller/bullet.png"));
        fireImg.setSize(sizeWidth, sizeHeight);
        fireImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                spacePressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                spacePressed = false;
            }
        });

        Table tableLeft = new Table();
        tableLeft.left().bottom();
        tableLeft.setFillParent(true);
        tableLeft.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight()).padRight(15);
        tableLeft.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
        tableLeft.padLeft(20).padBottom(20);

        Table tableRight = new Table();
        tableRight.right().bottom();
        tableRight.setFillParent(true);
        tableRight.add(fireImg).size(fireImg.getWidth(), fireImg.getHeight()).padRight(15);
        tableRight.add(jumpImg).size(jumpImg.getWidth(), jumpImg.getHeight());
        tableRight.padRight(20).padBottom(20);

        stage.addActor(tableLeft);
        stage.addActor(tableRight);
    }

    public void draw(){
        stage.draw();
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }
}