package com.meteor.blockinPractice.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static com.meteor.blockinPractice.Main.instance;

public class ConfigManager {
    private static FileConfiguration data;
    private static FileConfiguration island;
    public static void saveDefaultConfig() {
        File dataFile = new File(instance.getDataFolder(), "config.yml");
        File islandFile = new File(instance.getDataFolder(), "island.yml");
        if (!dataFile.exists()) {
            instance.saveResource("data.yml", false);
            data = YamlConfiguration.loadConfiguration(dataFile);
        }
        if (!islandFile.exists()) {
            instance.saveResource("island.yml", false);
            island = YamlConfiguration.loadConfiguration(islandFile);
        }
    }

    public static FileConfiguration getDataConfig() {
        return data;
    }

    public static FileConfiguration getIslandConfig() {
        return island;
    }

    public static void reloadConfig() {
        File dataFile = new File(instance.getDataFolder(), "data.yml");
        File islandFile = new File(instance.getDataFolder(), "island.yml");
        data = YamlConfiguration.loadConfiguration(dataFile);
        island = YamlConfiguration.loadConfiguration(islandFile);
    }

    public static void saveConfig() {
        File dataFile = new File(instance.getDataFolder(), "data.yml");
        File islandFile = new File(instance.getDataFolder(), "island.yml");
        instance.saveConfig();
        try {
            data.save(dataFile);
            island.save(islandFile);
        } catch (IOException ignored) { }
    }
}
