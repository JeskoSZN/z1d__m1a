package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    int x, y, w, h;
    String type;
    boolean selected, locked;
    ToolTip t;


    Button(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        w = (Tables.button_resources.get(type) == null ? Recources.cannon_button : Tables.button_resources.get(type)).getWidth();
        h = (Tables.button_resources.get(type) == null ? Recources.cannon_button : Tables.button_resources.get(type)).getHeight();
        selected = false;
        locked = true;

        if(!type.equals("close")) t = new ToolTip(type, this);


    }
    Rectangle hitbox(){ return new Rectangle(x, y, w, h);}

    void draw(SpriteBatch batch){

        batch.draw((Tables.button_resources.get(type) == null ? Recources.cannon_button : Tables.button_resources.get(type)), x, y);
        if(selected) batch.draw(Recources.button_selected, x - 7, y - 7);
        if(locked) batch.draw(Recources.button_locked,x ,y);
        if(t!=null) t.draw(batch);
    }


    void update(){
    }
}
