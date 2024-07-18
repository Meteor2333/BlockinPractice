package com.meteor.blockinPractice.Events;

import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.Tools;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Objects;

import static com.meteor.blockinPractice.Main.editMode;

public class BlockPlaceEvent implements Listener {
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
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent e) {
        getBedLocation(ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island"));
        int blockX = e.getBlockPlaced().getLocation().getBlockX();
        int blockY = e.getBlockPlaced().getLocation().getBlockY();
        int blockZ = e.getBlockPlaced().getLocation().getBlockZ();
        if (!editMode) {
            if (!((blockX <= bedHeadX + 4 && blockX >= bedHeadX - 4) || (blockX <= bedFootX + 4 && blockX >= bedFootX - 4))) e.setCancelled(true);
            if (blockY < bedHeadY || blockY < bedFootY || blockY > bedHeadY + 4 || blockY > bedFootY + 4) e.setCancelled(true);
            if (!((blockZ <= bedHeadZ + 4 && blockZ >= bedHeadZ - 4) || (blockZ <= bedFootZ + 4 && blockZ >= bedFootZ - 4))) e.setCancelled(true);
            Tools.updatePlayerBlocks(e.getPlayer());
        }

        //Anti via no sound
        final List<Material> optionalBlocks = List.of(
                Material.WHITE_WOOL,
                Material.LIGHT_GRAY_WOOL,
                Material.GRAY_WOOL,
                Material.BLACK_WOOL,
                Material.BROWN_WOOL,
                Material.RED_WOOL,
                Material.ORANGE_WOOL,
                Material.YELLOW_WOOL,
                Material.LIME_WOOL,
                Material.GREEN_WOOL,
                Material.CYAN_WOOL,
                Material.LIGHT_BLUE_WOOL,
                Material.BLUE_WOOL,
                Material.PURPLE_WOOL,
                Material.MAGENTA_WOOL,
                Material.PINK_WOOL);
        for (Material optionalBlock : optionalBlocks) {
            if (e.getBlockPlaced().getType() == optionalBlock) {
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_WOOL_PLACE, 1F, 1F);
            }
        }
    }
}
