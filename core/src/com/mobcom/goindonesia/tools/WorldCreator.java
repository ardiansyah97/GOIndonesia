package com.mobcom.goindonesia.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;
import com.mobcom.goindonesia.sprites.collectible.Chest;
import com.mobcom.goindonesia.sprites.collectible.Coin;
import com.mobcom.goindonesia.sprites.collectible.Health;
import com.mobcom.goindonesia.sprites.enemy.Barong;

import java.util.ArrayList;


/**
 * Created by Ardiansyah on 16/10/2017.
 */

public class WorldCreator {
    private Array<Barong> barongs;
    private ArrayList<Coin> coins;
    private ArrayList<Health> healts;
    private ArrayList<Chest> chests;

    public WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();


        //diagonal collision
        for(MapObject object : map.getLayers().get("polycoll").getObjects()){
            BodyDef bodyDef = new BodyDef();
            FixtureDef fixtureDef = new FixtureDef();
            Body body;
            Shape shape = getPolyline((PolylineMapObject)object);

            bodyDef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bodyDef);

            fixtureDef.filter.categoryBits = GOIndonesia.GROUND_BIT;
            fixtureDef.shape = shape;

            body.createFixture(shape, 1).setUserData(this);
        }

        //rectangle collioson
        for(MapObject object : map.getLayers().get("rectcoll").getObjects().getByType(RectangleMapObject.class)){
            BodyDef bodyDef = new BodyDef();
            FixtureDef fixtureDef = new FixtureDef();
            Body body;
            PolygonShape polygonShape = new PolygonShape();

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GOIndonesia.PPM, (rect.getY() + rect.getHeight() / 2) / GOIndonesia.PPM);

            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rect.getWidth() / 2 / GOIndonesia.PPM, rect.getHeight() / 2 / GOIndonesia.PPM);

            fixtureDef.filter.categoryBits = GOIndonesia.GROUND_BIT;
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef).setUserData(this);
        }

        //jurang collision
//        for(MapObject object : map.getLayers().get("jurang").getObjects().getByType(RectangleMapObject.class)){
//            BodyDef bodyDef = new BodyDef();
//            FixtureDef fixtureDef = new FixtureDef();
//            Body body;
//            PolygonShape polygonShape = new PolygonShape();
//
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GOIndonesia.PPM, (rect.getY() + rect.getHeight() / 2) / GOIndonesia.PPM);
//
//            body = world.createBody(bodyDef);
//            polygonShape.setAsBox(rect.getWidth() / 2 / GOIndonesia.PPM, rect.getHeight() / 2 / GOIndonesia.PPM);
//
//            fixtureDef.filter.categoryBits = GOIndonesia.JURANG_BIT;
//            fixtureDef.shape = polygonShape;
//            body.createFixture(fixtureDef);
//        }

        //create all barongs
        barongs = new Array<Barong>();
        for(MapObject object : map.getLayers().get("barong").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            barongs.add(new Barong(screen, rect.getX() / GOIndonesia.PPM, rect.getY() / GOIndonesia.PPM ));
        }

        //create all coins
        coins = new ArrayList<Coin>();
        for(MapObject object : map.getLayers().get("coin").getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellips= ((EllipseMapObject) object).getEllipse();
            coins.add(new Coin(screen, ellips.x / GOIndonesia.PPM, ellips.y / GOIndonesia.PPM));
        }

        //create all box
        chests = new ArrayList<Chest>();
        for(MapObject object : map.getLayers().get("box").getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellips= ((EllipseMapObject) object).getEllipse();
            chests.add(new Chest(screen, ellips.x / GOIndonesia.PPM, ellips.y / GOIndonesia.PPM));
        }


        //create all blood
        healts = new ArrayList<Health>();
        for(MapObject object : map.getLayers().get("blood").getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellips= ((EllipseMapObject) object).getEllipse();
            healts.add(new Health(screen, ellips.x / GOIndonesia.PPM, ellips.y / GOIndonesia.PPM));
        }



    }

    private static ChainShape getPolyline(PolylineMapObject polylineObject) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / 100;
            worldVertices[i].y = vertices[i * 2 + 1] / 100;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }

    public Array<Barong> getBarongs() {
        return barongs;
    }

    public ArrayList<Coin> getCoins(){
        return coins;
    }

    public  ArrayList<Health> getHealth() {
        return healts;
    }

    public ArrayList<Chest> getChest(){
        return chests;
    }
}
