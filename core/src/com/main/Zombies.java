package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Rectangle;

public class Zombies {
    int x, y, w, h, hp;
    int speed;
    String type;
    boolean active = true;
int rows = 1, cols = 4;
Animation anim;
TextureRegion[] frames;
TextureRegion frame;
float frame_time = 0.2f; //1 second = 1.0f



    Zombies(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = Tables.values.get("speed_" + type) == null ? 1 : Tables.values.get("speed_" + type);
        hp = Tables.values.get("health_" + type) == null ? 3 : Tables.values.get("health_" + type);
        w = (Tables.zombie_resources.get(type) == null ? Recources.zombie.getWidth() : Tables.zombie_resources.get(type).getWidth()) / cols;
        h = (Tables.zombie_resources.get(type) == null ? Recources.zombie.getHeight() : Tables.zombie_resources.get(type).getHeight()) / rows;
        init_animations();
    }


    void draw(SpriteBatch batch){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion) anim.getKeyFrame(frame_time, true);
        batch.draw(frame, x, y);
    }

    void update(){
        x -= speed;
        active = x + w > 0 && hp > 0;

    }
    void init_animations(){
        // split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split(
                        Tables.zombie_resources.get(type) == null ? Recources.zombie : Tables.zombie_resources.get(type),
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

    Rectangle hitbox(){ return new Rectangle(x, y, w, h);}


}
