package com.mobcom.goindonesia.tools;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mobcom.goindonesia.GOIndonesia;
import com.mobcom.goindonesia.sprites.enemy.Enemy;
import com.mobcom.goindonesia.sprites.other.Projectile;

/**
 * Created by Ardiansyah on 28/10/2017.
 */

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case GOIndonesia.ENEMY_BIT | GOIndonesia.GARUDA_BIT:
                System.out.println("Enemy and Garuda");
                break;
            case GOIndonesia.PROJECTILE_BIT | GOIndonesia.ENEMY_BIT:
                System.out.println("Projectile and Enemy");
                if(fixA.getFilterData().categoryBits == GOIndonesia.PROJECTILE_BIT) {
                    ((Projectile) fixA.getUserData()).setToDestroy();

                    ((Enemy) fixB.getUserData()).setEnemyHP(((Projectile)fixA.getUserData()).getDamage());


                }else {
                    ((Projectile) fixB.getUserData()).setToDestroy();

                    ((Enemy) fixA.getUserData()).setEnemyHP(((Projectile)fixB.getUserData()).getDamage());

                }
                break;
            case GOIndonesia.PROJECTILE_BIT | GOIndonesia.GROUND_BIT:
                System.out.println("Projectile and Ground");
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
