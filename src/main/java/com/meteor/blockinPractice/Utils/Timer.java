package com.meteor.blockinPractice.Utils;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.md_5.bungee.api.chat.TextComponent;

import static com.meteor.blockinPractice.Main.*;

public class Timer {
    public static void startTimer(Player player) {
        int islandId = ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island");
        timing[islandId] = 0F;
        new BukkitRunnable() {
            public void run() {
                if (timing[islandId] >= 120) {
                    new BukkitRunnable() {
                        public void run() {
                            ChatDisplay.outTime(player);
                            Waypoints.tpWaypoints(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0F);
                            this.cancel();
                        }
                    }.runTask(instance);
                    state[islandId] = false;
                    this.cancel();
                }
                if (!state[islandId]) this.cancel();
                else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§b§l" + String.format("%.2f", timing[islandId]) + "s"));
                    timing[islandId] += 0.05F;
                }
            }
        }.runTaskTimerAsynchronously(instance, 0, 1);
    }
}
