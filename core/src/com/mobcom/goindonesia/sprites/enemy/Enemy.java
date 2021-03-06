package com.mobcom.goindonesia.sprites.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.goindonesia.screens.PlayScreen;

/**
 * Created by Ardiansyah on 23/10/2017.
 */

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;

    public Enemy(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract boolean isDead();
    public abstract void setEnemyHP(int dmg);
    public abstract int getEnemyHp();
}
