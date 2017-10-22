package com.mobcom.goindonesia.sprites;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 21/10/2017.
 */

public class Coin extends Sprite {
    GOIndonesia game;
    World world;
    Body b2body;

    public Coin(World world, PlayScreen screen, float posX, float posY){
        super(screen.getAtlas().findRegion("g-one"));
        this.world = world;

        TextureRegion coin = new TextureRegion(getTexture(), 1, 6, 80, 90);

        defineCoin(posX / GOIndonesia.PPM , posY / GOIndonesia.PPM);

        game.batch.setProjectionMatrix(screen.getCam().combined);
        game.batch.begin();
        game.batch.draw(coin, 2, 5);
        game.batch.end();
    }

    public void defineCoin(float x, float y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / GOIndonesia.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
        shape.dispose();
    }
}
