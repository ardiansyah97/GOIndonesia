package com.mobcom.goindonesia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.scenes.Controller;
import com.mobcom.goindonesia.scenes.Hud;
import com.mobcom.goindonesia.sprites.collectible.Coin;
import com.mobcom.goindonesia.sprites.enemy.Enemy;
import com.mobcom.goindonesia.sprites.Garuda;
import com.mobcom.goindonesia.tools.B2WorldCreator;
import com.mobcom.goindonesia.tools.WorldContactListener;

import java.util.Iterator;

/**
 * Created by Ardiansyah on 02/10/2017.
 */

public class PlayScreen implements Screen {
    private GOIndonesia game;
    private TextureAtlas atlas;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private Controller controller;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer box2d;
    private B2WorldCreator creator;

    private Texture texture;

    private Garuda player;


    public PlayScreen(GOIndonesia game){
        atlas = game.assetManager.get("atlas/main_character.pack");

        this.game = game;
        texture = new Texture("background/bg_grasslands.png");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(GOIndonesia.V_WIDTH / GOIndonesia.PPM, GOIndonesia.V_HEIGHT / GOIndonesia.PPM, gameCam);

        controller = new Controller(game.batch);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map/level1b.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GOIndonesia.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        box2d = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);
        player = new Garuda(this);

        hud = new Hud(this);

        world.setContactListener(new WorldContactListener());

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.jump();

        if (controller.isRightPressed() && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0.6f, 0), player.b2body.getWorldCenter(), true);
            player.incGarudaHP(1);
        }

        if (controller.isLeftPressed() && player.b2body.getLinearVelocity().x >= -2){
            player.b2body.applyLinearImpulse(new Vector2(-0.6f, 0), player.b2body.getWorldCenter(), true);
            player.decGarudaHP(1);
            if(player.getGarudaHP()==0)
                game.setScreen(new GameOverScreen(game));
        }
    }

    public void update(float dt){
        handleInput();


        world.step(1/30f, 6, 2);

        player.update(dt);


        for(Coin coin : creator.getCoins()){
            coin.update(dt);
        }

        Iterator<Coin> iter = creator.getCoins().iterator();
        while(iter.hasNext()){
            Coin coin = iter.next();
            if(coin.getCollected()) {
                iter.remove();
                Hud.increaseCoin(1);
            }
        }

        for(Enemy enemy : creator.getBarongs())
            enemy.update(dt);

        if(player.b2body.getPosition().x > 5 && player.b2body.getPosition().x < 23)
            gameCam.position.x = player.b2body.getPosition().x;

        if(player.b2body.getPosition().y <= 0) {
            player.decGarudaHP(100);
            game.setScreen(new GameOverScreen(game));
        }

        if(player.b2body.getPosition().y > 2.5)
            gameCam.position.y = player.b2body.getPosition().y;

        //hud update
        hud.setHudHP(player.getGarudaHP());

        gameCam.update();
        renderer.setView(gameCam);
    }


    @Override
    public void render(float delta) {
        update(delta);
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(texture, 0, 0, 1067, 600);
        game.batch.end();

        renderer.render();

        box2d.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();

        player.draw(game.batch);

        for(Coin coin : creator.getCoins())
            coin.draw(game.batch);

        for(Enemy enemy : creator.getBarongs()) {
            //only draw enemy in screen
            if(Math.abs(player.getX() - enemy.getX()) < 12){
                enemy.draw(game.batch);
            }
        }

        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        controller.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        controller.resize(width, height);
        hud.resize(width, height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2d.dispose();
        hud.dispose();
    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthographicCamera getCam(){
        return gameCam;
    }

    public Garuda getPlayer(){
        return player;
    }

    public SpriteBatch getSpriteBatch(){
        return game.batch;
    }
}
