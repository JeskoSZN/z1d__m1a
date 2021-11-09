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
	static String current_type = "";

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
		/* UI */ UI.draw(batch);
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


	}

	void tap() {
		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();


			for(Button b : button) {
				if (b.t != null && !b.t.hidden && b.t.close.hitbox().contains(x, y)) { b.t.hidden = true; return; }
				if (b.t != null && !b.t.hidden && b.t.hitbox().contains(x,y)) return;

			}
			for(Button b : button) if(b.hitbox().contains(x, y)){
				if(b.locked) {
					if (b.t != null && b.t.hidden) {hide_tt(); b.t.hidden = false;}
					else {

						if(UI.money >= (Tables.values.get("unlock_" + current_type) == null ? 500 : Tables.values.get("unlock_" + current_type))) {
							UI.money -= (Tables.values.get("unlock_" + current_type) == null ? 500 : Tables.values.get("unlock_" + current_type));
							b.locked = false;
						}
						if (b.t != null) b.t.hidden = true;
					}
				}
				else{
					deselect();
					b.selected = true;
					current_type = b.type;
				}
				return;
			}


			for(Cannon c : cannon) if(c.hitbox().contains(x, y)) return;
			if (buildable(x, y)) cannon.add(new Cannon(current_type, x, y));
		}
	}

	void deselect(){
		for(Button b : button) b.selected = false;
	}

	void hide_tt(){
		for(Button b : button) if(b.t != null) b.t.hidden = true;
	}

	boolean buildable(int x, int y) {
		return ((y > 0 && y < 200) || (y > 300 && y < 500)) && x < 1000;



	}


	void setup() {
		Tables.init();
		button.add(new Button("ccc",225 + button.size() * 75, 525));
		button.add(new Button("fire",225 + button.size() * 75, 525));
		button.add(new Button("super",225 + button.size() * 75, 525));
		button.add(new Button("double",225 + button.size() * 75, 525));
		button.add(new Button("laser",225 + button.size() * 75, 525));


		}
	void spawn_zombies() {
		if(!zombies.isEmpty()) return;
		for (int i = 0; i < 5; i++) zombies.add (new Zombies("zzz", 526 + i * 50, r.nextInt(400)));

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
