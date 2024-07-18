package com.meteor.blockinPractice.Menu;

import com.meteor.blockinPractice.Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static com.meteor.blockinPractice.Main.instance;

public class IslandSelectionMenu {
    public static Inventory inv;
    private static BukkitRunnable refreshState;
    public static void initIslandSelectionMenu() {
        inv = Bukkit.createInventory(null, 54, ColorParser.parse("&dBlockin练习菜单-岛屿选择"));
        for (int i = 0; i < instance.getConfig().getInt("Config.IslandAmount"); i++) {
            if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + i + ".Player"), null)) {
                inv.setItem(i, new ItemStackFactory(Material.LIME_STAINED_GLASS_PANE)
                        .setDisplayName("&a" + ConfigManager.getIslandConfig().getString("Island." + i + ".IslandName"))
                        .addLore("&a点击以选择此岛屿")
                        .addLore("&7岛屿编号: " + i)
                        .toItemStack());
            } else {
                inv.setItem(i, new ItemStackFactory(Material.RED_STAINED_GLASS_PANE)
                        .setDisplayName("&c" + ConfigManager.getIslandConfig().getString("Island." + i + ".IslandName"))
                        .addLore("&c此岛屿已被占用")
                        .addLore("&7岛屿编号: " + i)
                        .toItemStack());
            }
        }
        inv.setItem(49, new ItemStackFactory(Material.BARRIER)
                .setDisplayName("&c返回")
                .addLore("&6点击以返回上级菜单")
                .toItemStack());
    }

    public static void openIslandSelectionMenu(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                refreshState = this;
                new BukkitRunnable() {
                    public void run() {
                        initIslandSelectionMenu();
                        player.openInventory(inv);
                    }
                }.runTask(instance);
            }
        }.runTaskTimerAsynchronously(instance, 0, 20);
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv) && e.getCurrentItem() != null) {
                    if (e.getSlot() == 49) {
                        refreshState.cancel();
                        player.closeInventory();
                        HandlerList.unregisterAll(this);
                        MainMenu.openMainMenu(player);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                    } else {
                        if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + e.getSlot() + ".Player"), null)) {
                            StructureBlocks.clearBlocks(ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island"));
                            ConfigManager.getIslandConfig().set("Island." + ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island") + ".Player", null);
                            ConfigManager.getIslandConfig().set("Island." + e.getSlot() + ".Player", player.getName());
                            ConfigManager.getDataConfig().set("Data." + player.getName() + ".Island", e.getSlot());
                            ConfigManager.saveConfig();
                            Waypoints.tpWaypoints(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 0F);
                            player.closeInventory();
                        } else {
                            ChatDisplay.islandOccupied(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0F);
                        }
                    }
                }
            }
            @EventHandler
            public void onInventoryClose(InventoryCloseEvent e) {
                if (player.equals(e.getPlayer()) && e.getInventory().equals(inv)) {
                    refreshState.cancel();
                    HandlerList.unregisterAll(this);
                }
            }
        }, instance);
    }
}
