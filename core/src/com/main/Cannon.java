package com.main;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;

public class Cannon {
    Sprite sprite;
    int x, y, w, h;
    String type;
    int counter = 0, delay = 30;

    Cannon(String type, int x, int y) {
        this.type = type;
        sprite = new Sprite(Tables.cannon_resources.get(type) == null ? Recources.cannon : Tables.cannon_resources.get(type));
        w = Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getWidth();
        h = Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getHeight();
        this.x = grid_lock(x - w / 2);
        this.y = grid_lock(y - h / 2);
        sprite.setPosition(this.x, this.y);
    }


    void draw(SpriteBatch batch) {
        sprite.draw(batch);


    }

    void update() {
        sprite.setRotation(get_angle());
    fire();

    }

    void fire(){
        if(counter++ >= delay){
            Main.bullets.add(new Bullet("bbb", x, y));
            counter = 0;
        }

    }

    float get_angle(){
        float zx = Main.zombies.get(0).x + Main.zombies.get(0).w / 2, zy = Main.zombies.get(0).y + Main.zombies.get(0).h / 2;
        return (float)Math.toDegrees(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
    }

    int grid_lock(int n) {
        return ((int)(n + 25) / 50) * 50;


    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h);}

}

