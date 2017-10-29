package com.mobcom.goindonesia.sprites.collectible;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 29/10/2017.
 */

public class Chest extends Collectible {

    private float stateTime;
    private TextureRegion textureRegion;

    public Chest(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        textureRegion = new TextureRegion(screen.getAtlas2().findRegion("chest"));
        stateTime = 0;
        setBounds(getX(), getY(), 72 / GOIndonesia.PPM, 60 / GOIndonesia.PPM);
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
        return false;
    }
}
