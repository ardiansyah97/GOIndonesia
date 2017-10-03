package com.mobcom.goindonesia.Map;




import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by Ardiansyah on 03/10/2017.
 */

public class BackgroundTiledMapRenderer extends OrthogonalTiledMapRenderer {

    private final Texture background;



    public BackgroundTiledMapRenderer(final TiledMap map, final float unitScale, final Batch batch, final Texture background) {
        super(map, unitScale, batch);
        this.background = background;
    }



    @Override

    protected void beginRender() {
        super.beginRender();
        // Draw the background
        getBatch().draw(background, viewBounds.x, viewBounds.y, viewBounds.getWidth(), viewBounds.getHeight());

    }



}