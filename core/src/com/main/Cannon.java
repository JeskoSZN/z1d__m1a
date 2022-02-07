package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Rectangle;
import java.util.Arrays;

public class Cannon {
    Sprite sprite;
    int x, y, w, h;
    String type;
    int counter = 0, delay;
    int timer = 0, time;
    boolean active = true, disabled = false;

    int rows = 1, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.2f; //1 second = 1.0f


    Cannon(String type, int x, int y) {
        this.type = type;
        cols  = Tables.values.get("columns_" + type) == null ? 1 : Tables.values.get("columns_" + type);
        sprite = new Sprite(Tables.cannon_resources.get(type) == null ? Recources.cannon : Tables.cannon_resources.get(type));
        w = (Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getWidth()) / cols;
        h = (Tables.cannon_resources.get(type) == null ? 50 : Tables.cannon_resources.get(type).getHeight()) / rows;
        this.x = grid_lock(x - w / 2);
        this.y = grid_lock(y - h / 2);
        sprite.setPosition(this.x, this.y);
        delay = Tables.values.get("delay_" + type) == null ? 30 : Tables.values.get("delay_" + type);
        time  = Tables.values.get("time_" + type) == null ? 500 : Tables.values.get("time_" + type);
        init_animations();
    }


    void draw(SpriteBatch batch) {
        sprite.draw(batch);
        if(disabled) { batch.draw(Recources.button_locked, x, y); return; }
        batch.draw(Recources.green_bar, x, y + h, (float)time * ((float)w /(float)time), 5);
        batch.draw(Recources.red_bar, x, y + h, (float)timer * ((float)w / (float)time), 5);
    }

    void update() {
        sprite.setRotation(get_angle());
        if(disabled) return;
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        sprite = new Sprite(frame);
        sprite.setPosition(this.x, this.y);
        sprite.setRotation(get_angle());
        fire();
        disabled = timer++ >= time;

    }

    void fire(){
        if(counter++ >= delay){
            ZTD.bullets.add(new Bullet("bbb", x, y));
            counter = 0;
        }

    }

    float get_angle(){
        float zx = 0, zy = 0;
        float[] difs = new float[ZTD.zombies.size()];
        int index = 0;
        for(Zombies z : ZTD.zombies){
            int dx = x - z.x, dy = y - z.y;
            difs[index++] = (float)Math.sqrt(dx * dx + dy * dy);

        }
        Arrays.sort(difs);
        float closest = difs[0];
        for(Zombies z : ZTD.zombies) {
            int dx = x - z.x, dy = y - z.y;
            if((float)Math.sqrt(dx * dx + dy * dy) == closest){
                zx = z.x + (float)z.w / 2;
                zy = z.y + (float)z.h / 2;

            }

        }

        return (float)Math.toDegrees((Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0)));
    }



    int grid_lock(int n) {
        return ((int)(n + 25) / 50) * 50;


    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h);}

    void init_animations(){
        // split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split(
                Tables.cannon_resources.get(type) == null ? Recources.cannon : Tables.cannon_resources.get(type),
                w,
                h
        );



        frames = new TextureRegion[rows * cols];



        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];



        anim = new Animation(frame_time, frames);
    }


}

