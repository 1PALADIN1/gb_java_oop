package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.GameInfo;
import com.mygdx.game.model.units.Unit;
import com.mygdx.game.model.units.UnitName;
import com.mygdx.game.model.units.UnitState;

public class MapRenderer {
    private final TextureAtlas atlas;
    private final GameInfo gameInfo;

    private final TextureAtlas.AtlasRegion defaultIdle;
    private final TextureAtlas.AtlasRegion defaultDead;
    private final TextureAtlas.AtlasRegion monkIdle;
    private final TextureAtlas.AtlasRegion monkDead;
    private final TextureAtlas.AtlasRegion wizardIdle;
    private final TextureAtlas.AtlasRegion wizardDead;
    private final TextureAtlas.AtlasRegion spearmanIdle;
    private final TextureAtlas.AtlasRegion spearmanDead;
    private final TextureAtlas.AtlasRegion robberIdle;
    private final TextureAtlas.AtlasRegion robberDead;
    private final TextureAtlas.AtlasRegion archerIdle;
    private final TextureAtlas.AtlasRegion archerDead;
    private final TextureAtlas.AtlasRegion sniperIdle;
    private final TextureAtlas.AtlasRegion sniperDead;

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

        atlas = new TextureAtlas("units/atlas.atlas");
        defaultIdle = atlas.findRegion("Peasant_Idle");
        defaultDead = atlas.findRegion("Peasant_Dead");
        monkIdle = atlas.findRegion("Monk_Idle");
        monkDead = atlas.findRegion("Monk_Dead");
        wizardIdle = atlas.findRegion("Wizard_Idle");
        wizardDead = atlas.findRegion("Wizard_Dead");
        spearmanIdle = atlas.findRegion("Spearman_Idle");
        spearmanDead = atlas.findRegion("Spearman_Dead");
        robberIdle = atlas.findRegion("Robber_Idle");
        robberDead = atlas.findRegion("Robber_Dead");
        archerIdle = atlas.findRegion("Crossbowman_Idle");
        archerDead = atlas.findRegion("Crossbowman_Dead");
        sniperIdle = atlas.findRegion("Sniper_Idle");
        sniperDead = atlas.findRegion("Sniper_Dead");
    }

    public void render(SpriteBatch batch) {
        defaultIdle.flip(true, false);
        defaultDead.flip(true, false);
        for (int i = gameInfo.getGangSize() - 1; i >= 0; i--) {
            Unit darkUnit = gameInfo.getDarkSide().get(i);

            float x = startRenderPoint.x + (darkUnit.getPosition().x - 1) * gridSize.x;
            float y = startRenderPoint.y + (darkUnit.getPosition().y - 1) * gridSize.y;
            TextureAtlas.AtlasRegion region = darkUnit.getState() == UnitState.DEAD
                    ? getDead(darkUnit.getName())
                    : getIdle(darkUnit.getName());
            if (!region.isFlipX()) {
                region.flip(true, false);
            }
            batch.draw(region, x, y);
        }

        defaultIdle.flip(true, false);
        defaultDead.flip(true, false);
        for (int i = gameInfo.getGangSize() - 1; i >= 0; i--) {
            Unit whiteUnit = gameInfo.getWhiteSide().get(i);

            float x = startRenderPoint.x + (whiteUnit.getPosition().x - 1) * gridSize.x;
            float y = startRenderPoint.y + (whiteUnit.getPosition().y - 1) * gridSize.y;
            TextureAtlas.AtlasRegion region = whiteUnit.getState() == UnitState.DEAD
                    ? getDead(whiteUnit.getName())
                    : getIdle(whiteUnit.getName());
            if (region.isFlipX()) {
                region.flip(true, false);
            }
            batch.draw(region, x, y);
        }
    }

    public void dispose () {
        atlas.dispose();
    }

    private TextureAtlas.AtlasRegion getIdle(String unitName) {
        switch (unitName) {
            case UnitName.MONK:
                return monkIdle;
            case UnitName.WIZARD:
                return wizardIdle;
            case UnitName.SPEARMAN:
                return spearmanIdle;
            case UnitName.ROBBER:
                return robberIdle;
            case UnitName.CROSSBOWMAN:
                return archerIdle;
            case UnitName.SNIPER:
                return sniperIdle;
            default:
                return defaultIdle;
        }
    }

    private TextureAtlas.AtlasRegion getDead(String unitName) {
        switch (unitName) {
            case UnitName.MONK:
                return monkDead;
            case UnitName.WIZARD:
                return wizardDead;
            case UnitName.SPEARMAN:
                return spearmanDead;
            case UnitName.ROBBER:
                return robberDead;
            case UnitName.CROSSBOWMAN:
                return archerDead;
            case UnitName.SNIPER:
                return sniperDead;
            default:
                return defaultDead;
        }
    }
}
