package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.model.Game;
import com.mygdx.game.view.MapRenderer;

public class MyGdxGame extends ApplicationAdapter {
	private static final int GANG_SIZE = 10;
	private static final float TICK = 2.0f;

	private SpriteBatch batch;
	private Texture background;

	private MapRenderer mapRenderer;
	private Game game;
	private float timeLeft;

	@Override
	public void create () {
		game = new Game(GANG_SIZE);
		batch = new SpriteBatch();

		background = new Texture("background.png");
		mapRenderer = new MapRenderer(game);

		timeLeft = TICK;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		float deltaTime = Gdx.graphics.getDeltaTime();
		timeLeft -= deltaTime;
		if (timeLeft <= 0) {
			game.nextTurn();
			timeLeft = TICK;
		}

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mapRenderer.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		mapRenderer.dispose();
	}
}
