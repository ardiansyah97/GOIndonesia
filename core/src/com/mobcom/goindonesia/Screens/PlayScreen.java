package com.mobcom.goindonesia.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.Scenes.Controller;
import com.mobcom.goindonesia.Scenes.Hud;
import com.mobcom.goindonesia.Sprites.Dani;

/**
 * Created by Ardiansyah on 02/10/2017.
 */

public class PlayScreen implements Screen {
    private GOIndonesia game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private Controller controller;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer box2d;

    private Texture texture;

    public PlayScreen(GOIndonesia game){
        this.game = game;
        texture = new Texture("bg_grasslands.png");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(GOIndonesia.V_WIDTH / GOIndonesia.PPM, GOIndonesia.V_HEIGHT / GOIndonesia.PPM, gameCam);
        hud = new Hud(game.batch);
        controller = new Controller(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GOIndonesia.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);
        box2d = new Box2DDebugRenderer();

//        BodyDef bodyDef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fixtureDef = new FixtureDef();
//        Body body;

//        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GOIndonesia.PPM, (rect.getY() + rect.getHeight() / 2) / GOIndonesia.PPM);
//
//            body = world.createBody(bodyDef);
//            shape.setAsBox(rect.getWidth() / 2 / GOIndonesia.PPM, rect.getHeight() / 2 / GOIndonesia.PPM);
//
//            fixtureDef.shape = shape;
//            body.createFixture(fixtureDef);
//        }
//
//        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GOIndonesia.PPM, (rect.getY() + rect.getHeight() / 2) / GOIndonesia.PPM);
//
//            body = world.createBody(bodyDef);
//            shape.setAsBox(rect.getWidth() / 2 / GOIndonesia.PPM, rect.getHeight() / 2 / GOIndonesia.PPM);
//
//            fixtureDef.shape = shape;
//            body.createFixture(fixtureDef);
//        }
//
//        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GOIndonesia.PPM, (rect.getY() + rect.getHeight() / 2) / GOIndonesia.PPM);
//
//            body = world.createBody(bodyDef);
//            shape.setAsBox(rect.getWidth() / 2 / GOIndonesia.PPM, rect.getHeight() / 2 / GOIndonesia.PPM);
//
//            fixtureDef.shape = shape;
//            body.createFixture(fixtureDef);
//        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(controller.isRightPressed()) {
            gameCam.position.x += 10 * dt;
        }else if (controller.isLeftPressed()) {
            gameCam.position.x -= 10 * dt;
        }else if (controller.isDownPressed()) {
            gameCam.position.y -= 10 * dt;
        }else if (controller.isUpPressed()) {
            gameCam.position.y += 10 * dt;
        }
    }

    public void update(float dt){
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }




    @Override
    public void render(float delta) {
        update(delta);
//        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.batch.draw(texture, 0, 0, 1067, 600);
        game.batch.end();

        renderer.render();

        box2d.render(world, gameCam.combined);

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
