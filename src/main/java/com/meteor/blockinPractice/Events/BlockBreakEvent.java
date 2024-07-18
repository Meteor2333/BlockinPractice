package com.meteor.blockinPractice.Events;

import com.meteor.blockinPractice.Utils.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

import static com.meteor.blockinPractice.Main.*;

public class BlockBreakEvent implements Listener {
    static int bedHeadX;
    static int bedHeadY;
    static int bedHeadZ;
    static int bedFootX;
    static int bedFootY;
    static int bedFootZ;

    public static void getBedLocation(int IslandId) {
        bedHeadX = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Head")).getBlockX();
        bedHeadY = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Head")).getBlockY();
        bedHeadZ = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Head")).getBlockZ();
        bedFootX = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Foot")).getBlockX();
        bedFootY = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Foot")).getBlockY();
        bedFootZ = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Foot")).getBlockZ();
    }

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
        e.setDropItems(false);
        getBedLocation(ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island"));
        int blockX = e.getBlock().getLocation().getBlockX();
        int blockY = e.getBlock().getLocation().getBlockY();
        int blockZ = e.getBlock().getLocation().getBlockZ();
        if (!editMode) {
            if (blockX > bedHeadX + 4 || blockX > bedFootX + 4 || blockX < bedHeadX - 4 || blockX < bedFootX - 4) e.setCancelled(true);
            if (blockY < bedHeadY || blockY < bedFootY || blockY > bedHeadY + 4 || blockY > bedFootY + 4) e.setCancelled(true);
            if (blockZ > bedHeadZ + 4 || blockZ > bedFootZ + 4 || blockZ < bedHeadZ - 4 || blockZ < bedFootZ - 4) e.setCancelled(true);
        } int islandId = ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island");
        if (e.getBlock().getType() == Material.RED_BED && state[islandId] && !editMode) {
            state[islandId] = false;
            timing[islandId] = Float.parseFloat(String.format("%.2f", timing[islandId]));
            if (CheckBlockinUsability.checkBlockin(e.getPlayer().getLocation().getBlock())) {
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0F);
                e.getPlayer().sendTitle("§b用时: §e" + timing[islandId] + "s", "§c无效成绩", 10, 20, 10);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§b§l" + timing[islandId] + "s"));
                ChatDisplay.invalidBlockin(e.getPlayer());
            } else {
                int coins = CoinFactory.coinCalculating(e.getPlayer(), islandId);
                e.getPlayer().teleport(Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island") + ".WaypointPos")));
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 0F);
                e.getPlayer().sendTitle("§b用时: §e" + timing[islandId] + "s", "§6+ " + coins + " 金币", 10, 20, 10);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§b§l" + timing[islandId] + "s"));
                if (ConfigManager.getDataConfig().getDouble("Data." + e.getPlayer().getName() + ".MinTime") == 0 ||
                        ConfigManager.getDataConfig().getDouble("Data." + e.getPlayer().getName() + ".MinTime") > timing[islandId])
                    ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".MinTime", timing);
                ConfigManager.getDataConfig().set("Data." + e.getPlayer().getName() + ".Coins", ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Coins") + coins);
                ConfigManager.saveConfig();
            } Waypoints.tpWaypoints(e.getPlayer());
            e.setCancelled(true);
        }
    }
}
