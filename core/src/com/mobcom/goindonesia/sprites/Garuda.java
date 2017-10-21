package com.mobcom.goindonesia.sprites;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 03/10/2017.
 */

public class Garuda extends Sprite{
    public enum State{FALLING, JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion garudaStand;

    private Animation garudaRun;
    private Animation garudaJump;

    private float stateTimer;
    private boolean runningRight;


    public Garuda(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("g-one"));
        this.world = world;


        currentState = State.STANDING;
        previousState = State.STANDING;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 3; i++){
            frames.add(new TextureRegion(getTexture(), i*80 + 1, 6, 80,90 ));
        }
        garudaRun = new Animation(0.1f, frames);
        frames.clear();

        frames.add(new TextureRegion(getTexture(), 241, 0, 80, 90));
        frames.add(new TextureRegion(getTexture(), 321, 6, 80, 90));

        garudaJump = new Animation(0.2f, frames);

        garudaStand = new TextureRegion(getTexture(), 1, 6, 80, 90);
        defineGaruda();
        setBounds(0,0, 80/GOIndonesia.PPM, 90/GOIndonesia.PPM);
        setRegion(garudaStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            case JUMPING:
                region = (TextureRegion) garudaJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region  = (TextureRegion) garudaRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = garudaStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;

        previousState = currentState;

        return region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().y > 0 || b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING)
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void defineGaruda(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(2, 5);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(40 / GOIndonesia.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
        shape.dispose();
    }

    public void jump(){
        if ( currentState != State.JUMPING ) {
            b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }
}
