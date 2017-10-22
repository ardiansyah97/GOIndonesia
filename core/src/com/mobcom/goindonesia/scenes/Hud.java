package com.mobcom.goindonesia.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.sprites.Garuda;

/**
 * Created by Ardiansyah on 02/10/2017.
 */

public class Hud implements Disposable{
    public Garuda player;
    public Stage stage;
    private Viewport viewport;

    private static int worldLife;
    private static int worldCoin;
    private static int weapon;
    private static int peti;
    private static String worldIsland;


    private boolean timeUp;
    private float timeCount;

    private static Label lifeLabel;
    private static Label islandLabel;
    private static Label petiLabel;
    private static Label weaponLabel;
    private static Label coinLabel;

    private static TextureRegion leftHud, rightHud;

    private static TextureAtlas atlas;

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public Hud(SpriteBatch sb){
        atlas = GOIndonesia.getAssetManager().get("atlas/hud.pack");

        worldLife = 100;
        worldCoin = 33333;
        peti = 9;
        weapon = 9;
        worldIsland = "SUMATERA";

        timeCount = 0;

        viewport = new FitViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);



        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        style.font = GOIndonesia.getAssetManager().get("font/my-font.fnt");

        lifeLabel = new Label(String.valueOf(worldLife), style);
        lifeLabel.setFontScale(0.8f);

        islandLabel = new Label(worldIsland, style);
        islandLabel.setFontScale(0.8f);

        petiLabel = new Label(String.format("%02d", peti), style);
        petiLabel.setFontScale(0.8f);

        weaponLabel = new Label(String.format("%02d", weapon), style);
        weaponLabel.setFontScale(0.8f);

        coinLabel = new Label(String.format("%05d", worldCoin), style);
        coinLabel.setFontScale(0.8f);

        leftHud = new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 63*2+1, 145,63);
        rightHud = new TextureRegion(atlas.findRegion("hud").getTexture(), 150, 10, 580,63);

        Table tableLeft = new Table();
        tableLeft.left().top();
        tableLeft.setFillParent(true);
        tableLeft.add(new Image(leftHud)).padTop(10).padLeft(10);

        Table tableRight= new Table();
        tableRight.right().top();
        tableRight.setFillParent(true);
        tableRight.add(new Image(rightHud)).padTop(10);

        Table tableLife = new Table();
        tableLife.left().top();
        tableLife.setFillParent(true);
        tableLife.add(lifeLabel).padTop(30).padLeft(95);

        Table tableIsland = new Table();
        tableIsland.top();
        tableIsland.setFillParent(true);
        tableIsland.add(islandLabel).padTop(30).padLeft(75);

        Table tablePeti = new Table();
        tablePeti.top();
        tablePeti.setFillParent(true);
        tablePeti.add(petiLabel).padTop(30).padLeft(445);

        Table tableWeapon= new Table();
        tableWeapon.right().top();
        tableWeapon.setFillParent(true);
        tableWeapon.add(weaponLabel).padTop(30).padRight(160);

        Table tableCoin= new Table();
        tableCoin.right().top();
        tableCoin.setFillParent(true);
        tableCoin.add(coinLabel).padTop(30).padRight(10);

        stage.addActor(tableLeft);
        stage.addActor(tableRight);
        stage.addActor(tableLife);
        stage.addActor(tableIsland);
        stage.addActor(tablePeti);
        stage.addActor(tableWeapon);
        stage.addActor(tableCoin);
    }

    public static void increaseLife(int incHp){
        if(worldLife==100)
            lifeLabel.setText(String.valueOf(worldLife));
        else {
            if(worldLife + incHp > 100) {
                worldLife = 100;
                lifeLabel.setText(String.valueOf(worldLife));
            } else {
                worldLife += incHp;
                lifeLabel.setText(String.valueOf(worldLife));
            }

            if(worldLife>=65)
                leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 63*2+1, 145,63));
            else if(worldLife>=30)
                leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 63+1, 145,63));
            else
                leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 1, 145,63));
        }
    }

    public static void decreaseLife(int decHp){
        if(worldLife==0) {
            lifeLabel.setText(String.valueOf(worldLife));
        } else {
            if(worldLife - decHp <= 0) {
                worldLife = 0;
                lifeLabel.setText(String.valueOf(worldLife));
            }else {
                worldLife -= decHp;
                lifeLabel.setText(String.valueOf(worldLife));
            }

            if(worldLife>=75)
                leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 63*2+1, 145,63));
            else if(worldLife>=40)
                leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 63+1, 145,63));
            else
                leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 1, 145,63));
        }
    }

    public static void increaseCoin(int incCoin){
        worldCoin++;
        coinLabel.setText(String.format("%05d", worldCoin));
    }

    public static int getLife(){
        return worldLife;
    }

    /* untuk timer
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            if (worldLife > 0) {
                worldLife--;
            } else {
                timeUp = true;
            }
            lifeLabel.setText(String.format("%03d", worldLife));
            timeCount = 0;
        }
    }
    */

//    public static void addScore(int value){
//        score += value;
////        scoreLabel.setText(String.format("%06d", score));
//    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isTimeUp() { return timeUp; }
}
