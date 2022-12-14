package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.GameInfo;
import com.mygdx.game.model.units.Unit;

public class MapRenderer {
    private final TextureAtlas atlas;
    private final GameInfo gameInfo;

    private final TextureAtlas.AtlasRegion unitIdle;
    private final TextureAtlas.AtlasRegion unitDead;

    // левый нижний угол поля
    private final Vector2 startRenderPoint;
    // правый верхний угол поля
    private final Vector2 endRenderPoint;

    // размер сетки
    private final Vector2 gridSize;

    public MapRenderer(GameInfo gameInfo) {
        this.gameInfo = gameInfo;

        startRenderPoint = new Vector2(50, 50);
        endRenderPoint = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - 100);

        float gridX = (endRenderPoint.x - startRenderPoint.x) / gameInfo.getGangSize();
        float gridY = (endRenderPoint.y - startRenderPoint.y) / gameInfo.getGangSize();
        gridSize = new Vector2(gridX, gridY);

        atlas = new TextureAtlas("units/peasant.atlas");
        unitIdle = atlas.findRegion("Peasant_Idle");
        unitDead = atlas.findRegion("Peasant_Dead");
    }

    public void render(SpriteBatch batch) {
        for (int i = gameInfo.getGangSize() - 1; i >= 0; i--) {
            Unit whiteUnit = gameInfo.getWhiteSide().get(i);
            Unit darkUnit = gameInfo.getDarkSide().get(i);

            float x = startRenderPoint.x + (darkUnit.getPosition().x - 1) * gridSize.x;
            float y = startRenderPoint.y + (darkUnit.getPosition().y - 1) * gridSize.y;
            unitIdle.flip(true, false);
            batch.draw(unitIdle, x, y);

            x = startRenderPoint.x + (whiteUnit.getPosition().x - 1) * gridSize.x;
            y = startRenderPoint.y + (whiteUnit.getPosition().y - 1) * gridSize.y;
            unitIdle.flip(true, false);
            batch.draw(unitIdle, x, y);
        }
    }

    public void dispose () {
        atlas.dispose();
    }
}
