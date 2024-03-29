package com.main;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class mButton {
    /*
       public void draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX,
		float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY);
         */
    //THIS CLASS IS DESIGNED TO BUILD CUSTOM BUTTONS BASED ON A GIVEN ASSET
    int x, y, w, h, ox, oy, sx, sy, sw, sh;
    String type;
    BitmapFont font = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();
    public static final int bw = 150;
    public static final int bh = 75;


    mButton(String type, int x, int y, int w, int h){
        while((layout.width <  w - 2 * (float)(w / 7) && layout.height < h - 2 * (float)(h / 7))) {
            font.getData().setScale(font.getData().scaleX + 0.1f);

            layout.setText(font,
                    type.equals("start") ? "Start" : type.equals("exit") ? "Exit" : type.equals("about") ? "About" : "Button"


            );
        }
        this.type = type;
        this.x = ox = x;
        this.y = oy = y;
        this.w = w;
        this.h = h;
        sx = 0;
        sy = 0;
        sw = 15;
        sh = 15;
    }

    void draw(SpriteBatch batch){
        batch.draw(
                Recources.button_start,
                x,
                y,
                ox,
                oy,
                w,
                h,
                1f,
                1f,
                0f,
                sx,
                sy,
                sw,
                sh,
                false,
                false
        );

        font.draw(batch, layout , x + (float)(w / 2) - (layout.width / 2), y + (float)(h / 2) + (layout.height / 2));
    }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h); }
}
