package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class About {
    mButton m1;

    About(){
        m1 = new mButton("back", (1024 / 2) - (mButton.bw / 2), 325, mButton.bw, mButton.bh);
    }
    void handle_clicks(int x, int y){
        if(m1.hitbox().contains(x, y)) {
            System.out.println("CLICKED ON " + m1);
            Main.about = false;
        }

    }
    void draw(SpriteBatch batch){
        ScreenUtils.clear(new Color(0x03101500));
        m1.draw(batch);
    }
}
