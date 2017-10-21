package com.mobcom.goindonesia.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.goindonesia.GOIndonesia;

/**
 * Created by Ardiansyah on 16/10/2017.
 */

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        Shape shape;
        FixtureDef fixtureDef = new FixtureDef();
        Body body;


        for(MapObject object : map.getLayers().get(2).getObjects()){
            shape = getPolyline((PolylineMapObject)object);;

            bodyDef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bodyDef);

            fixtureDef.shape = shape;

            body.createFixture(shape, 1);
        }

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GOIndonesia.PPM, (rect.getY() + rect.getHeight() / 2) / GOIndonesia.PPM);

            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rect.getWidth() / 2 / GOIndonesia.PPM, rect.getHeight() / 2 / GOIndonesia.PPM);

            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
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

}
