package com.meteor.blockinPractice;

import com.meteor.blockinPractice.Commands.MainCommands;
import com.meteor.blockinPractice.Events.*;
import com.meteor.blockinPractice.Utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {
    public static Main instance;
    public static boolean[] state = new boolean[50];
    public static float[] timing = new float[50];
    public static boolean editMode = false;

    @Override
    public void onEnable() {
        instance = this;
        Arrays.fill(state, false);
        Arrays.fill(timing, 0F);
        if (Bukkit.getWorld("world") == null) {
            Bukkit.getConsoleSender().sendMessage("此插件仅限主世界使用, 请不要关闭主世界生成, 插件已卸载");
            Bukkit.getPluginManager().disablePlugin(instance);
        }
        loadPlugin();
        checkConfig();
    }

    public void loadPlugin() {
        instance.saveDefaultConfig();
        ConfigManager.saveDefaultConfig();
        instance.reloadConfig();
        ConfigManager.reloadConfig();
        Objects.requireNonNull(this.getCommand("bp")).setExecutor(new MainCommands());
        Objects.requireNonNull(this.getCommand("bp")).setTabCompleter(new MainCommands());
        Objects.requireNonNull(this.getCommand("blockinpractice")).setExecutor(new MainCommands());
        Objects.requireNonNull(this.getCommand("blockinpractice")).setTabCompleter(new MainCommands());
        Bukkit.getPluginManager().registerEvents(this,this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChangeInventoryEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRightClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageEvent(),this);
    }

    public void checkConfig() {
        if (instance.getConfig().getInt("Config.IslandAmount") > 45) {
            Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件加载失败, 岛屿数量超过限制, 可能由于手动更改了配置文件");
            Bukkit.getPluginManager().disablePlugin(instance);
            return;
        }
        if (getConfig().getInt("Config.IslandAmount") == 0) {
            Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件加载失败, 进入编辑模式, 此模式下不会触发开始事件, 设置完成后重载插件即可退出此模式");
            editMode = true;
        } else for (int i = 0; i < getConfig().getInt("Config.IslandAmount"); i++) {
            if (ConfigManager.getIslandConfig().get("Island." + i + ".Bed") == null ||
                ConfigManager.getIslandConfig().get("Island." + i + ".IslandName") == null ||
                ConfigManager.getIslandConfig().get("Island." + i + ".WaypointPos") == null) {
                Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件加载失败, 进入编辑模式, 此模式下不会触发开始事件, 设置完成后重载插件即可退出此模式");
                editMode = true;
                return;
            }
        }
    }

    @EventHandler
    public void onWeatherChange(org.bukkit.event.weather.WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(org.bukkit.event.entity.FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }

    @Override
    public void onDisable() {
        for (int i = 0; i < instance.getConfig().getInt("Config.IslandAmount"); i++) {
            ConfigManager.getIslandConfig().set("Island." + i + ".Player", null);
        } ConfigManager.saveConfig();
        instance = null;
    }
}
