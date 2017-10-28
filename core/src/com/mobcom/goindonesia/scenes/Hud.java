package com.mobcom.goindonesia.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 02/10/2017.
 */

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private static int worldHP;
    private static int worldCoin;
    private static int weapon;
    private static int peti;
    private static String worldIsland;


    private static Label hpLabel;
    private static Label islandLabel;
    private static Label petiLabel;
    private static Label weaponLabel;
    private static Label coinLabel;

    private static TextureRegion leftHud, rightHud;

    private static TextureAtlas atlas;

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public Hud(PlayScreen screen){
        atlas = GOIndonesia.getAssetManager().get("atlas/hud.pack");

        worldHP = screen.getPlayer().getGarudaHP();
        worldCoin = GOIndonesia.getCoins();
        peti = 0;
        weapon = 0;
        worldIsland = "JAWA";

        viewport = new FitViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, screen.getSpriteBatch());


        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        style.font = GOIndonesia.getAssetManager().get("font/my-font.fnt");

        hpLabel = new Label(String.valueOf(worldHP), style);
        hpLabel.setFontScale(0.7f);

        islandLabel = new Label(worldIsland, style);
        islandLabel.setFontScale(0.7f);

        petiLabel = new Label(String.format("%02d", peti), style);
        petiLabel.setFontScale(0.7f);

        weaponLabel = new Label(String.format("%02d", weapon), style);
        weaponLabel.setFontScale(0.7f);

        coinLabel = new Label(String.format("%05d", worldCoin), style);
        coinLabel.setFontScale(0.7f);

        leftHud = new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 57*2+1, 140,57);
        rightHud = new TextureRegion(atlas.findRegion("hud").getTexture(), 150, 10, 500,57);


        Table tableLeft = new Table();
        tableLeft.left().top();
        tableLeft.setFillParent(true);

        tableLeft.add(new Image(leftHud)).padTop(10).padLeft(10);

        Table tableRight= new Table();
        tableRight.right().top();
        tableRight.setFillParent(true);
        tableRight.add(new Image(rightHud)).padTop(10);

        float padTop = 28;

        Table tableLife = new Table();
        tableLife.left().top();
        tableLife.setFillParent(true);
        tableLife.add(hpLabel).padTop(padTop).padLeft(85);

        Table tableIsland = new Table();
        tableIsland.top();
        tableIsland.setFillParent(true);
        tableIsland.add(islandLabel).padTop(padTop).padLeft(165);

        Table tablePeti = new Table();
        tablePeti.top();
        tablePeti.setFillParent(true);
        tablePeti.add(petiLabel).padTop(padTop).padLeft(525);

        Table tableWeapon= new Table();
        tableWeapon.right().top();
        tableWeapon.setFillParent(true);
        tableWeapon.add(weaponLabel).padTop(padTop).padRight(135);

        Table tableCoin= new Table();
        tableCoin.right().top();
        tableCoin.setFillParent(true);
        tableCoin.add(coinLabel).padTop(padTop).padRight(5);

        stage.addActor(tableLeft);
        stage.addActor(tableRight);
        stage.addActor(tableLife);
        stage.addActor(tableIsland);
        stage.addActor(tablePeti);
        stage.addActor(tableWeapon);
        stage.addActor(tableCoin);
    }


    public void setHudHP(int HP){
        hpLabel.setText(String.valueOf(HP));
        if(HP >=65)
            leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 57*2+1, 140,57));
        else if(HP >=30)
            leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 57+1, 140,57));
        else
            leftHud.setRegion(new TextureRegion(atlas.findRegion("hud").getTexture(), 1 , 1, 140,57));
    }

    public static void increaseCoin(int incCoin){
        worldCoin++;
        coinLabel.setText(String.format("%05d", worldCoin));
        GOIndonesia.setCoins(worldCoin);
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    @Override
    public void dispose() { stage.dispose(); }

}
