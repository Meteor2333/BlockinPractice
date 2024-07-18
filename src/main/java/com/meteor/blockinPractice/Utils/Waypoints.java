package com.meteor.blockinPractice.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.meteor.blockinPractice.Main.editMode;

public class Waypoints {
    public static void tpWaypoints(Player player) {
        if (editMode) return;
        player.setFallDistance(0.0F);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setAllowFlight(true);
        player.setNoDamageTicks(10);
        StructureBlocks.placeStructure(player);
        player.setLevel(ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Coins"));
        player.teleport(Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island") + ".WaypointPos")));
    }

    public static Location getWaypointLocation(Player p) {
        return ConfigManager.getIslandConfig().getLocation("Island." + ConfigManager.getDataConfig().getInt("Data." + p.getName() + ".Island") + ".WaypointPos");
    }
}
