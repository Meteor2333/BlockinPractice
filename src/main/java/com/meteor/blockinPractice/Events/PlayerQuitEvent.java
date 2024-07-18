package com.meteor.blockinPractice.Events;

import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.StructureBlocks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerQuitEvent implements Listener {
    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent e) {
        StructureBlocks.clearBlocks(ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island"));
        ConfigManager.getIslandConfig().set("Island." + ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island") + ".Player", null);
        ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Island", null);
        ConfigManager.saveConfig();
    }
}