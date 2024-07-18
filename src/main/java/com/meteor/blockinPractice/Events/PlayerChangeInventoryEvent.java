package com.meteor.blockinPractice.Events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.meteor.blockinPractice.Main.editMode;

public class PlayerChangeInventoryEvent implements Listener {
    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE && e.getWhoClicked() instanceof Player && !editMode) e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(org.bukkit.event.inventory.InventoryDragEvent e) {
        if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE && e.getWhoClicked() instanceof Player && !editMode) e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(org.bukkit.event.player.PlayerDropItemEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE && !editMode) e.setCancelled(true);
    }
}
