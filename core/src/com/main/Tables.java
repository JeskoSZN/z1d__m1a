package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Tables {
    static HashMap<String, Texture> cannon_resources = new HashMap<String, Texture>();
    static HashMap<String, Texture> zombie_resources = new HashMap<String, Texture>();
    static HashMap<String, Texture> button_resources = new HashMap<String, Texture>();
    static HashMap<String, Texture> resources = new HashMap<String, Texture>();
    static HashMap<String, String > tooltip_info = new HashMap<String, String>();
    static HashMap<String, Integer > values = new HashMap<String, Integer>();



    static void init(){
        resources.put("effect_boom", Recources.boom);
        resources.put("effect_muzzleflash", Recources.muzzleflash);
        resources.put("effect_click", Recources.click);


        cannon_resources.put("fire", Recources.cannon_fire);
        cannon_resources.put("super", Recources.cannon_super);
        cannon_resources.put("double", Recources.cannon_double);
        cannon_resources.put("laser", Recources.cannon_laser);
        cannon_resources.put("mounted", Recources.cannon_mounted);

        button_resources.put("mounted", Recources.cannon_mounted_button);
        button_resources.put("fire", Recources.cannon_fire_button);
        button_resources.put("super", Recources.cannon_super_button);
        button_resources.put("double", Recources.cannon_double_button);
        button_resources.put("laser", Recources.cannon_laser_button);
        button_resources.put("close", Recources.button_close);
        button_resources.put("wall", Recources.button_wall);
        button_resources.put("pause", Recources.button_pause);
        button_resources.put("play", Recources.button_play);
        button_resources.put("start", Recources.button_start);
        button_resources.put("exit", Recources.button_exit);


        tooltip_info.put("fire", "Fires some bullets at some rate of fire");
        tooltip_info.put("super", "Fires some bullets at some rate of fire");
        tooltip_info.put("double","Fires some bullets at some rate of fire" );
        tooltip_info.put("laser","Fires some bullets at some rate of fire" );
        tooltip_info.put("mounted", "Fires some bullets at some rate of fire");


        zombie_resources.put("dif", Recources.zombie_dif);
        zombie_resources.put("speedy", Recources.zombie_speedy);
        zombie_resources.put("fast", Recources.zombie_fast);
        zombie_resources.put("riot", Recources.zombie_riot);

        values.put("place_fire", 30);
        values.put("place_super", 25);
        values.put("place_laser", 100);
        values.put("place_double", 20);

        values.put("unlock_fire", 30);
        values.put("unlock_super", 500);
        values.put("unlock_laser", 1000);
        values.put("unlock_double", 250);

        //CANNON FIRE DELAY
        values.put("delay_fire", 10);
        values.put("delay_laser", 100);
        values.put("delay_double", 40);


        values.put("speed_dif", 1);
        values.put("speed_speedy", 5);
        values.put("speed_fast", 3);
        values.put("speed_riot", 1);

        values.put("health_dif", 2);
        values.put("health_speedy", 2);
        values.put("health_fast", 3);
        values.put("health_riot", 10);

        //ANIMATION COLUMNS
        values.put("columns_laser", 16);
        values.put("columns_speedy", 6);
        values.put("columns_boom", 5);

    }
}
