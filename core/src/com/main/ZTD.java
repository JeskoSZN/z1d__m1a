package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class ZTD {

    static String current_type = "";
    static boolean pause = false;
    static Random r;

    ZTD(){
        r = new Random();
        setup();
    }


    static ArrayList<Zombies> zombies = new ArrayList<>();
    ArrayList<Cannon> cannon = new ArrayList<>();
    ArrayList<Button> button = new ArrayList<>();
    static ArrayList<Bullet> bullets = new ArrayList<>();
    static ArrayList<Wall> walls = new ArrayList<>();
    static ArrayList<Effect> Effects = new ArrayList<>();

    public void update() {

        spawn_zombies();


        if (!pause) {

            for (Zombies z : zombies) z.update();
            for (Cannon c : cannon) c.update();
            for (Button b : button) b.update();
            for (Bullet b : bullets) b.update();
            for (Wall w : walls) w.update();

        }
        housekeeping();
        Main.gameover = UI.life <= 0;


    }

    void tap(int x, int y) {




            Effects.add(new Effect("boom", x, y));


            for (Button b : button) {
                if (b.t != null && !b.t.hidden && b.t.close.hitbox().contains(x, y)) {
                    b.t.hidden = true;
                    return;
                }
                if (b.t != null && !b.t.hidden && b.t.hitbox().contains(x, y)) return;

            }
            for (Button b : button)
                if (b.hitbox().contains(x, y)) {
                    if (b.type.equals("pause") || b.type.equals("play")) {
                        pause = !pause;
                        b.type = pause ? "play" : "pause";
                        return;

                    }

                    if (b.locked) {
                        if (b.t != null && b.t.hidden) {
                            hide_tt();
                            b.t.hidden = false;
                        } else {

                            if (UI.money >= (Tables.values.get("unlock_" + current_type) == null ? 500 : Tables.values.get("unlock_" + current_type))) {
                                UI.money -= (Tables.values.get("unlock_" + current_type) == null ? 500 : Tables.values.get("unlock_" + current_type));
                                b.locked = false;
                            }
                            if (b.t != null) b.t.hidden = true;
                        }
                    } else {
                        if (b.type.equals("wall") || b.type.equals("mounted")) {
                            walls.add(new Wall(walls.size() * 50, 0, b.type.equals("mounted")));
                            return;
                        }
                        deselect();
                        b.selected = true;
                        current_type = b.type;
                    }
                    return;
                }


            for (Cannon c : cannon)
                if (c.hitbox().contains(x, y)) {

                    if (c.disabled) c.active = false;
                    return;
                }
        if (UI.money >= (Tables.values.get("place_" + current_type) == null ? 15 : Tables.values.get("place_" + current_type))) {
            UI.money -= (Tables.values.get("place_" + current_type) == null ? 15 : Tables.values.get("place_" + current_type));
            cannon.add(new Cannon((current_type), x, y));
        }

            if (buildable(x, y)) cannon.add(new Cannon(current_type, x, y));
        }


    void draw(SpriteBatch batch) {
        /* bg */
        batch.draw(Recources.bg, 0, 0);
        /* UI */
        UI.draw(batch);
        for (Zombies z : zombies) z.draw(batch);
        for (Cannon c : cannon) c.draw(batch);
        for (Button b : button) b.draw(batch);
        for (Bullet b : bullets) b.draw(batch);
        for (Wall w : walls) w.draw(batch);
        for (Effect e : Effects) e.draw(batch);
    }

    void deselect() {
        for (Button b : button) b.selected = false;
    }

    void hide_tt() {
        for (Button b : button) if (b.t != null) b.t.hidden = true;
    }

    boolean buildable(int x, int y) {
        return ((y > 0 && y < 200) || (y > 300 && y < 500)) && x < 1000;
    }
        void setup () {
        zombies.clear();
        cannon.clear();
        button.clear();
        bullets.clear();
        walls.clear();
        Effects.clear();
            UI.money = 9999;
            UI.wave = 0;
            UI.score = 0;
            UI.life = 20;
            Tables.init();
            button.add(new Button("ccc", 225 + button.size() * 75, 525));
            button.add(new Button("fire", 225 + button.size() * 75, 525));
            button.add(new Button("super", 225 + button.size() * 75, 525));
            button.add(new Button("double", 225 + button.size() * 75, 525));
            button.add(new Button("laser", 225 + button.size() * 75, 525));
            button.add(new Button("wall", 225 + button.size() * 75, 525));
            button.get(button.size() - 1).locked = false;
            button.get(button.size() - 1).selected = false;
            button.add(new Button("mounted", 225 + button.size() * 75, 525));
            button.add(new Button("pause", 1024 - 75, 525));
            button.get(button.size() - 1).locked = false;
            button.get(button.size() - 1).selected = false;
        }
        void spawn_zombies () {
            if (!zombies.isEmpty()) return;
            for (int i = 0; i < 5; i++)
                zombies.add(new Zombies("riot", 526 + i * 50, r.nextInt(400)));

        }

        void housekeeping () {
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
            for (Cannon c : cannon)
                if (!c.active) {
                    cannon.remove(c);
                    break;
                }
            for (Wall w : walls)
                if (!w.active) {
                    walls.remove(w);
                    break;
                }

            for (Effect e : Effects)
                if (!e.active) {
                    Effects.remove(e);
                    break;
                }
        }
    }

