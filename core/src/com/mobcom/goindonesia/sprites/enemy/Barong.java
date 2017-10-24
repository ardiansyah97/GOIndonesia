package com.mobcom.goindonesia.sprites.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 23/10/2017.
 */

public class Barong extends Enemy {

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public Barong(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 3; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("g-one"), i*80 + 1, 6, 80,90 ));
        }
        walkAnimation = new Animation(0.3f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 80 / GOIndonesia.PPM, 90 / GOIndonesia.PPM);
    }



    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(40 / GOIndonesia.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
        shape.dispose();
    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime, true));
    }
}
