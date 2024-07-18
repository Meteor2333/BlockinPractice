package com.meteor.blockinPractice.Commands.SubCommands;

import com.meteor.blockinPractice.Utils.ConfigManager;
import org.bukkit.entity.Player;

import static com.meteor.blockinPractice.Main.instance;

public class AddIslandCommand {
    public static void command(Player player, String islandName) {
        if (instance.getConfig().getInt("Config.IslandAmount") >= 45) {
            player.sendMessage("§b§lBlockinPractice §7§l>> §c岛屿创建失败, 岛屿限制为45个");
            return;
        }
        ConfigManager.getIslandConfig().set("Island." + instance.getConfig().getInt("Config.IslandAmount") + ".IslandName", islandName);
        player.sendMessage("§b§lBlockinPractice §7§l>> §c岛屿创建成功, 岛屿编号为" + instance.getConfig().getInt("Config.IslandAmount") + ".");
        instance.getConfig().set("Config.IslandAmount", instance.getConfig().getInt("Config.IslandAmount") + 1);
        ConfigManager.saveConfig();
        instance.saveConfig();
    }
}
