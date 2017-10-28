package com.mobcom.goindonesia.sprites.other;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;

import java.awt.geom.RectangularShape;

/**
 * Created by Ardiansyah on 27/10/2017.
 */

public class Projectile extends Sprite {

    private World world;
    private Body b2body;
    private float speed;
    private boolean destroyed;
    private boolean isRight;

    public Projectile(PlayScreen screen, float x, float y, boolean isRight){
        this.world = screen.getWorld();
        this.isRight = isRight;

        TextureRegion textureRegion = new TextureRegion(screen.getAtlas().findRegion("bullet"));
        if(isRight)
            setPosition(x + (38/GOIndonesia.PPM),y + (8/GOIndonesia.PPM));
        else
            setPosition(x - (43/GOIndonesia.PPM),y + (8/GOIndonesia.PPM));
        setBounds(getX(), getY(), 14 / GOIndonesia.PPM, 5 / GOIndonesia.PPM);
        setRegion(textureRegion);
        defineProjectile();
    }

    public void defineProjectile(){
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        if(!world.isLocked())
            b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(14 / GOIndonesia.PPM);


        fdef.filter.categoryBits = GOIndonesia.PROJECTILE_BIT;
        fdef.filter.maskBits = GOIndonesia.GROUND_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    float x = 0;
    public void update(float dt){
        if(Math.abs(x) > 0.25f)
            destroyed = true;

        if (isRight)
            speed = 0.01f;
        else
            speed = -0.01f;

        x += speed;
        setPosition(getX() + x, getY());
    }

    public void setDestroyed(){
        destroyed = true;
    }

    public boolean isDestroyed(){
        return destroyed;
    }
}
