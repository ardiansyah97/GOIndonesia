package com.mobcom.goindonesia.sprites.collectible;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 23/10/2017.
 */

public abstract class Collectible extends Sprite {
    protected World world;
    protected PlayScreen screen;

    public Collectible(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineCoin();
    }

    protected abstract void defineCoin();
    public abstract void update(float dt);
    public abstract boolean getCollected();
}
