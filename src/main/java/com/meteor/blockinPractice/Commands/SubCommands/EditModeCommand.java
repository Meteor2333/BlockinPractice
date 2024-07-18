package com.meteor.blockinPractice.Commands.SubCommands;

import org.bukkit.entity.Player;

import static com.meteor.blockinPractice.Main.editMode;

public class EditModeCommand {
    public static void command(Player player) {
        if (editMode) player.sendMessage("§b§lBlockinPractice §7§l>> §c请重载插件以退出此模式.");
        else {
            player.sendMessage("§b§lBlockinPractice §7§l>> §c进入编辑模式, 此模式下不会触发开始事件.");
            editMode = true;
        }
    }
}
