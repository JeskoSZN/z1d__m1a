package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Random r;

	static ArrayList<Zombies> zombies = new ArrayList<>();
	ArrayList<Cannon> cannon = new ArrayList<>();
	ArrayList<Button> button = new ArrayList<>();
	static ArrayList<Bullet> bullets = new ArrayList<>();

	@Override
	public void create() {
		batch = new SpriteBatch();
		r = new Random();
		setup();

	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		update();
		batch.begin();
		/* bg */
		batch.draw(Recources.bg, 0, 0);
		for (Zombies z : zombies) z.draw(batch);
		for (Cannon c : cannon) c.draw(batch);
		for (Button b : button) b.draw(batch);
		for (Bullet b : bullets) b.draw(batch);

		batch.end();
	}



	public void update() {

		spawn_zombies();
		tap();

		for (Zombies z : zombies) z.update();
		for (Cannon c : cannon) c.update();
		for (Button b : button) b.update();
		for (Bullet b : bullets) b.update();

		housekeeping();
		System.out.println(cannon.size());

	}

	void tap() {
		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();
			if (buildable(x, y)) cannon.add(new Cannon("ccc", x, y));


			for(Cannon c : cannon) if(c.hitbox().contains(x, y)) return;
			if (buildable(x, y)) cannon.add(new Cannon("super", x, y));
		}
	}

	boolean buildable(int x, int y) {
		return ((y > 0 && y < 200) || (y > 300 && y < 500)) && x < 1000;



	}


	void setup() {
		Tables.init();
		for (int i = 0; i < 5; i++) button.add(new Button("bbb",25 + i * 75, 525));

		}
	void spawn_zombies() {
		if(!zombies.isEmpty()) return;
		for (int i = 0; i < 5; i++) zombies.add (new Zombies("zzz", 526 + i * 50, r.nextInt(400),5));

	}

	void housekeeping() {
		for (Zombies z : zombies)
			if (!z.active) {
				zombies.remove(z);
				break;
			}
		for (Bullet b : bullets)
			if (!b.active) {
				bullets.remove(b);
				break;
			}
	}
	@Override
	public void dispose() {
		batch.dispose();
	}
}
