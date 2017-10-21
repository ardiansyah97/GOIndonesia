package com.mobcom.goindonesia.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.sprites.Garuda;

/**
 * Created by Ardiansyah on 02/10/2017.
 */

public class Hud implements Disposable{

    public Stage stage;
    private Viewport viewport;

    private static int worldLife;
    private boolean timeUp;
    private float timeCount;
    private static Integer score;

    private static Label lifeLabel;
    private static Label scoreLabel;
    private Label levelLabel;

    private TextureAtlas atlas;

    private static final String SMALL_CARROT_REGION = "carrot-small";

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public Hud(SpriteBatch sb){

        atlas = new TextureAtlas("ninja-rabbit.pack");


        worldLife = 100;
        timeCount = 0;
        score = 0;


        viewport = new FitViewport(GOIndonesia.V_WIDTH, GOIndonesia.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        lifeLabel = new Label(String.format("%03d", worldLife), new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel =new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.RED));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.RED));

        table.row();
        table.add(new Image(atlas.findRegion(SMALL_CARROT_REGION))).expandX().padTop(10);
//        table.add(scoreLabel).expandX().padTop(10);
//        table.add(levelLabel).expandX().padTop(10);
//        table.add(lifeLabel).expandX().padTop(10);

        Table table2 = new Table();
        table2.top();
        table2.setFillParent(true);
        table2.add(lifeLabel).expandX().padTop(10);
//        table2.add(scoreLabel).expandX().padTop(10);
//        table2.add(levelLabel).expandX().padTop(10);
//        table2.add(lifeLabel).expandX().padTop(10);

        stage.addActor(table);
        stage.addActor(table2);

    }


    public static void increaseLife(int incHp){
        if(worldLife==100)
            lifeLabel.setText(String.format("%03d", worldLife));
        else {
            if(worldLife + incHp > 100) {
                worldLife = 100;
                lifeLabel.setText(String.format("%03d", worldLife));
            } else {
                worldLife += incHp;
                lifeLabel.setText(String.format("%03d", worldLife));
            }
        }
    }

    public static void decreaseLife(int decHp){
        if(worldLife==0)
            lifeLabel.setText(String.format("%03d", worldLife));
        else {
            worldLife -= decHp;
            lifeLabel.setText(String.format("%03d", worldLife));
        }
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

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isTimeUp() { return timeUp; }
}
