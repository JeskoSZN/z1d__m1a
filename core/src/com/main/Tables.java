package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Tables {
    static HashMap<String, Texture> cannon_resources = new HashMap<String, Texture>();
    static HashMap<String, Texture> zombie_resources = new HashMap<String, Texture>();
    static HashMap<String, Texture> button_resources = new HashMap<String, Texture>();
    static HashMap<String, String > tooltip_info = new HashMap<String, String>();

    static void init(){
        cannon_resources.put("fire", Recources.cannon_fire);
        cannon_resources.put("super", Recources.cannon_super);
        cannon_resources.put("double", Recources.cannon_double);
        cannon_resources.put("laser", Recources.cannon_laser);
        cannon_resources.put("mounted", Recources.cannon_mounted);

        button_resources.put("mounted", Recources.cannon_mounted_button);
        button_resources.put("fire", Recources.cannon_fire_button);
        button_resources.put("super", Recources.cannon_super_button);
        button_resources.put("double", Recources.cannon_double_button);
        button_resources.put("close", Recources.button_close);


        tooltip_info.put("fire", "Fires some bullets at some rate of fire");
        tooltip_info.put("super", "Fires some bullets at some rate of fire");
        tooltip_info.put("double","Fires some bullets at some rate of fire" );
        tooltip_info.put("laser","Fires some bullets at some rate of fire" );
        tooltip_info.put("mounted", "Fires some bullets at some rate of fire");


        zombie_resources.put("dif", Recources.zombie_dif);
        zombie_resources.put("speedy", Recources.zombie_speedy);
        zombie_resources.put("fast", Recources.zombie_fast);
        zombie_resources.put("riot", Recources.zombie_riot);

    }
}
