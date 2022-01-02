package com.me.keiko.teammanagerplugins.file;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


//設置custom config , 自動生成在同config.yml的Folder中
public class CustomConfig {

    private static File file;
    private static FileConfiguration customfile;

    public static void setFile() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("TeamManagerPlugins").getDataFolder(),"customconfig.yml");

        if(!file.exists()) {

            try {
                file.createNewFile();
            }catch (IOException exception){

            }
        }
        customfile = YamlConfiguration.loadConfiguration(file);

    }
    public static FileConfiguration get() {
        return customfile;
    }

    public static void save() {
        try {
            customfile.save(file);
        }catch (IOException exception){
            System.out.println("there is an exception!!");
        }


    }

    public static void reload() {
        customfile = YamlConfiguration.loadConfiguration(file);
    }



}
