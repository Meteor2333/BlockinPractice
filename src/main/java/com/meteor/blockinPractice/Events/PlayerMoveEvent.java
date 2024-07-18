package com.meteor.blockinPractice.Events;

import com.meteor.blockinPractice.Utils.ChatDisplay;
import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.Waypoints;
import com.meteor.blockinPractice.Utils.Timer;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.meteor.blockinPractice.Main.*;

public class PlayerMoveEvent implements Listener {
    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent e) {
        if (editMode) return;
        int islandId = ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island");
        if (e.getPlayer().getLocation().getY() <= 0) {
            state[islandId] = false;
            timing[islandId] = 0F;
            ChatDisplay.outWorld(e.getPlayer());
            Waypoints.tpWaypoints(e.getPlayer());
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0F);
        }
        if (e.getPlayer().getLocation().getY() < Waypoints.getWaypointLocation(e.getPlayer()).getBlock().getY() && !state[islandId]) {
            state[islandId] = true;
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 0F);
            Timer.startTimer(e.getPlayer());
        }
    }
}
