package com.meteor.blockinPractice.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDamageEvent implements Listener {
    @EventHandler
    public void onPlayerDamage(org.bukkit.event.entity.EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) e.setCancelled(true);
        e.setDamage(0.0);
    }
}
