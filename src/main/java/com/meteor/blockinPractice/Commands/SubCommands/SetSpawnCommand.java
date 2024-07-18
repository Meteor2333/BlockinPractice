package com.meteor.blockinPractice.Commands.SubCommands;

import org.bukkit.entity.Player;
import static com.meteor.blockinPractice.Main.instance;

public class SetSpawnCommand {
    public static void command(Player player) {
        instance.getConfig().set("Config.Spawn", player.getLocation());
        player.sendMessage("§b§lBlockinPractice §7§l>> §c成功保存出生点.");
        instance.saveConfig();
    }
}
