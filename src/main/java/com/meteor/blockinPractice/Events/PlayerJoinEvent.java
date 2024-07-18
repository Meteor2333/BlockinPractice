package com.meteor.blockinPractice.Events;

import com.meteor.blockinPractice.Main;
import com.meteor.blockinPractice.Utils.*;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

import static com.meteor.blockinPractice.Main.instance;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e) {
        if (e.getPlayer().getName().equals("Meteor23333"))
            e.getPlayer().sendMessage("§b§lBlockinPractice §7§l>> §c此服务器使用了BlockinPractice插件.");
        e.getPlayer().teleport(Objects.requireNonNull(instance.getConfig().getLocation("Config.Spawn")));
        e.getPlayer().setGameMode(GameMode.SPECTATOR);
        if (Main.editMode && e.getPlayer().isOp()) {
            ChatDisplay.help(e.getPlayer());
            e.getPlayer().sendMessage("§b§lBlockinPractice §7§l>> §c当前为编辑模式.");
            e.getPlayer().setGameMode(GameMode.CREATIVE);
            return;
        } else if (Main.editMode) return;
        if (ConfigManager.getDataConfig().get("Data." + e.getPlayer().getName() + ".Tools") == null) {
            ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Tools", 1);
        } if (ConfigManager.getDataConfig().get("Data." + e.getPlayer().getName() + ".Selection") == null) {
            ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Selection.First", new ItemStackFactory(Material.END_STONE).toItemStack());
            ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Selection.Second", new ItemStackFactory(Material.OAK_PLANKS).toItemStack());
            ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Selection.Third", new ItemStackFactory(Material.AIR).toItemStack());
        } if (ConfigManager.getDataConfig().get("Data." + e.getPlayer().getName() + ".Block") == null) {
            ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Block", new ItemStackFactory(Material.WHITE_WOOL).toItemStack());
        } ConfigManager.saveConfig();
        for (int i = 0; i < instance.getConfig().getInt("Config.IslandAmount"); i++) {
            if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + i + ".Player"), null)) {
                ConfigManager.getIslandConfig().set("Island." + i + ".Player", e.getPlayer().getName());
                ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Island", i);
                ConfigManager.saveConfig();
                e.getPlayer().setGameMode(GameMode.SURVIVAL);
                Waypoints.tpWaypoints(e.getPlayer());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 0F);
                Tools.givePlayerTools(e.getPlayer());
                return;
            }
        } e.getPlayer().sendMessage("§c所有岛屿均已被占用, 请稍后尝试.");
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_FALL, 1F, 0F);
    }
}
