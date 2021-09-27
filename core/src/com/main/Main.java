package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Zombies zombie = new Zombies("zzzz", 526, 300, 5);

	ArrayList<Zombies> zombies = new ArrayList<>();
	
	@Override
	public void create () {
batch = new SpriteBatch();


	for (int i = 0; i < 1000; i++){
		zombies.add(new Zombies("zzzz", 526 + i * 50, 300, 5));
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		update();
		batch.begin();
		for(Zombies z : zombies) z.draw(batch);
		zombie.draw(batch);
		batch.end();
	}

	public void update(){
		zombie.update();
		for(Zombies z : zombies) z.update();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
