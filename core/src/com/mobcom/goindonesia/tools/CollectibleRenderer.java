package com.mobcom.goindonesia.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;
import com.mobcom.goindonesia.sprites.Coin;
import com.mobcom.goindonesia.sprites.Garuda;

/**
 * Created by Ardiansyah on 21/10/2017.
 */

public class CollectibleRenderer {
    Coin coin;

    public CollectibleRenderer(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        float posX, posY;

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            System.out.println("X = "+ellipse.x+" & Y = "+ellipse.y);
            posX = ellipse.x;
            posY = ellipse.y;
            coin = new Coin(world, screen, posX, posY);
        }

    }

}
