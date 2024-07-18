package com.meteor.blockinPractice.Commands.SubCommands;

import com.meteor.blockinPractice.Utils.ConfigManager;
import org.bukkit.entity.Player;

public class SetWaypointCommand {
    public static void command(Player player, String islandId) {
        ConfigManager.getIslandConfig().set("Island." + islandId + ".WaypointPos", player.getLocation());
        player.sendMessage("§b§lBlockinPractice §7§l>> §c成功保存岛屿编号为" + islandId + "的传送点.");
        ConfigManager.saveConfig();
    }
}
