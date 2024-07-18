package com.meteor.blockinPractice.Events;

import com.meteor.blockinPractice.Menu.MainMenu;
import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.Waypoints;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.meteor.blockinPractice.Main.editMode;
import static com.meteor.blockinPractice.Main.state;

public class PlayerRightClickEvent implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            int islandId = ConfigManager.getDataConfig().getInt("Data." + e.getPlayer().getName() + ".Island");
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_SWORD) {
                if (state[islandId] && !editMode) {
                    state[islandId] = false;
                    Waypoints.tpWaypoints(e.getPlayer());
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0F);
                }
            } else if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
                if (state[islandId]) {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0F);
                    Waypoints.tpWaypoints(e.getPlayer());
                    state[islandId] = false;
                } else if (!editMode) MainMenu.openMainMenu(e.getPlayer());
            }
        }
    }
}
