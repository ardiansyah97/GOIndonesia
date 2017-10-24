package com.mobcom.goindonesia.sprites.collectible;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 21/10/2017.
 */

public class Coin extends Collectible {

    private float stateTime;
    private TextureRegion textureRegion;
    private boolean isCollected;

    public Coin(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        textureRegion = new TextureRegion(screen.getAtlas().findRegion("g-one"), 1, 6, 80, 90);
        stateTime = 0;
        isCollected = false;
        setBounds(getX(), getY(), 80 / GOIndonesia.PPM, 90 / GOIndonesia.PPM);
    }

    @Override
    protected void defineCoin() {

    }

    @Override
    public void update(float dt) {
        stateTime += dt;
        setRegion(textureRegion);
    }

    @Override
    public boolean getCollected() {
        float x = screen.getPlayer().getX() - getX();
        float y = screen.getPlayer().getY() - getY();

        if(Math.abs(x) < 0.5 && Math.abs(y) < 0.5) {
            isCollected = true;
            //play music
        } else
            isCollected = false;

        return isCollected;
    }
}