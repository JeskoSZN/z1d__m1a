package com.main;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Start {
    mButton m1, m2, m3;


    Start(){

        m1 = new mButton("start", (1024 / 2) - (mButton.bw / 2), 325, mButton.bw, mButton.bh);
        m2 = new mButton("about", (1024 / 2) - (mButton.bw / 2), 200, mButton.bw, mButton.bh);
        m3 = new mButton("exit", (1024 / 2) - (mButton.bw / 2), 75, mButton.bw, mButton.bh);
    }

    void handle_clicks(int x, int y){
        if(m1.hitbox().contains(x, y)) {
            System.out.println("CLICKED ON " + m1);
            Main.started = true;
        }
            if(m2.hitbox().contains(x, y)) {
                System.out.println("CLICKED ON " + m1);
                Main.about = true;
            }
        }



    void draw(SpriteBatch batch){
        batch.draw(Recources.bg_start, 0, 0);
        m1.draw(batch);
        m2.draw(batch);
        m3.draw(batch);
    }
}

