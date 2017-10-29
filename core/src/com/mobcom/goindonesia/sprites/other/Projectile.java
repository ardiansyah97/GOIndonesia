package com.mobcom.goindonesia.sprites.other;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;


/**
 * Created by Ardiansyah on 27/10/2017.
 */

public class Projectile extends Sprite {

    World world;
    Body b2body;
    BodyDef bdef;
    int damage;
    float stateTime;
    boolean setToDestroy;
    boolean destroyed;
    boolean isRight;

    public Projectile(PlayScreen screen, float x, float y, boolean isRight){
        this.world = screen.getWorld();
        this.isRight = isRight;

        damage = 5;

        TextureRegion textureRegion = new TextureRegion(screen.getAtlas().findRegion("bullet"));

        setRegion(textureRegion);
        setBounds(x, y, 14 / GOIndonesia.PPM, 5 / GOIndonesia.PPM);
        defineProjectile();
    }

    public void defineProjectile(){
        bdef = new BodyDef();
        bdef.position.set(isRight ? getX() + (36/GOIndonesia.PPM) : getX() - (42/GOIndonesia.PPM),getY() + (7/GOIndonesia.PPM));
        bdef.type = BodyDef.BodyType.KinematicBody;
        if(!world.isLocked())
            b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/ GOIndonesia.PPM);

        fdef.filter.categoryBits = GOIndonesia.PROJECTILE_BIT;
        fdef.filter.maskBits = GOIndonesia.ENEMY_BIT | GOIndonesia.GROUND_BIT;
        fdef.shape = shape;

        b2body.createFixture(fdef).setUserData(this);
        b2body.setLinearVelocity(new Vector2(isRight? 1f : -1f,0));
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - 7/GOIndonesia.PPM,  b2body.getPosition().y);
        stateTime += dt;
        if((stateTime > 3 || setToDestroy) && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
        }

    }

    public int getDamage(){
        return damage;
    }

    public void setToDestroy(){
        setToDestroy = true;
    }

    public boolean isDestroyed(){
        return destroyed;
    }

}
