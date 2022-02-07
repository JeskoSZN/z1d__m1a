package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


import java.util.Arrays;

public class Bullet {

        int x, y, w, h, speed;
        float angle;
        String type;
        boolean active = true;

        Bullet(String type, int x, int y){
            this.type = type;
            this.x = x;
            this.y = y;
            w = Recources.bullet.getWidth();
            h = Recources.bullet.getHeight();
            speed = 5;
            angle = get_angle();
        }


        void draw(SpriteBatch batch){
            batch.draw(Recources.bullet, x, y);
        }

        void update(){
            x += Math.cos(angle) * speed;
            y += Math.sin(angle) * speed;
            hit_detect();

        }

        float get_angle(){
            //float zx = Main.zombies.get(0).x + Main.zombies.get(0).w / 2, zy = Main.zombies.get(0).y + Main.zombies.get(0).h / 2;
            //return (float)(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
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

            return (float)(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
        }

    Rectangle hitbox(){ return new Rectangle(x, y, w, h);}


    void hit_detect(){
            if(ZTD.zombies.isEmpty()) return;
            for(Zombies z : ZTD.zombies) if(z.hitbox().contains((hitbox()))) {
                active = false;
                z.hp--;
            }
    }


}
