package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.ScreenUtils;

public class Go {
    mButton m1;

    Go(){
        m1 = new mButton("back", (1024 / 2) - (mButton.bw / 2), 325, mButton.bw, mButton.bh);
    }
    void handle_clicks(int x, int y){
        if(m1.hitbox().contains(x, y)) {
            Main.started = false;
            Main.about = false;
            Main.gameover = false;
            Main.ztd = new ZTD();
        }

    }
    void draw(SpriteBatch batch){
        ScreenUtils.clear(new Color(0xaa101015));
        m1.draw(batch);
    }
}
