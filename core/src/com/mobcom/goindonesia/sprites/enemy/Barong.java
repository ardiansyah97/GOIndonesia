package com.mobcom.goindonesia.sprites.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.scenes.Hud;
import com.mobcom.goindonesia.screens.PlayScreen;

import javax.security.sasl.SaslServer;

/**
 * Created by Ardiansyah on 23/10/2017.
 */

public class Barong extends Enemy {

    private int barongHP;
    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;
    private boolean dead;

    public Barong(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(screen.getAtlas().findRegion("weapon"), 81, 85, 80,70 ));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("weapon"), 161, 85, 75,70 ));

        walkAnimation = new Animation(0.3f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 80 / GOIndonesia.PPM, 70 / GOIndonesia.PPM);
        barongHP = 20;
    }


    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        if(!world.isLocked())
            b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(34 / GOIndonesia.PPM);

        fdef.filter.categoryBits = GOIndonesia.ENEMY_BIT;
        fdef.filter.maskBits = GOIndonesia.PROJECTILE_BIT |  GOIndonesia.GROUND_BIT ;// | GOIndonesia.GARUDA_BIT;
        fdef.shape = shape;
        fdef.friction = 10;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime, true));

        if(dead){
            world.destroyBody(b2body);
            dead = true;
        }

    }


    @Override
    public void setEnemyHP(int dmg) {
        barongHP -= dmg;
        if(barongHP <= 0){
            dead = true;
            Hud.increaseCoin(3);
        }
    }

    @Override
    public int getEnemyHp() {
        return barongHP;
    }

    @Override
    public boolean isDead(){
        return dead;
    }
}
