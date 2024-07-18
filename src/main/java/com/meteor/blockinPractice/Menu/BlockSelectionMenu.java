package com.meteor.blockinPractice.Menu;

import com.meteor.blockinPractice.Main;
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

import java.util.List;

public class BlockSelectionMenu {
    private static final List<Material> optionalBlocks = List.of(
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
            Material.PINK_WOOL,
            Material.WHITE_CONCRETE,
            Material.LIGHT_GRAY_CONCRETE,
            Material.GRAY_CONCRETE,
            Material.BLACK_CONCRETE,
            Material.BROWN_CONCRETE,
            Material.RED_CONCRETE,
            Material.ORANGE_CONCRETE,
            Material.YELLOW_CONCRETE,
            Material.LIME_CONCRETE,
            Material.GREEN_CONCRETE,
            Material.CYAN_CONCRETE,
            Material.LIGHT_BLUE_CONCRETE,
            Material.BLUE_CONCRETE,
            Material.PURPLE_CONCRETE,
            Material.MAGENTA_CONCRETE,
            Material.PINK_CONCRETE,
            Material.WHITE_STAINED_GLASS,
            Material.LIGHT_GRAY_STAINED_GLASS,
            Material.GRAY_STAINED_GLASS,
            Material.BLACK_STAINED_GLASS,
            Material.BROWN_STAINED_GLASS,
            Material.RED_STAINED_GLASS,
            Material.ORANGE_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS,
            Material.LIME_STAINED_GLASS,
            Material.GREEN_STAINED_GLASS,
            Material.CYAN_STAINED_GLASS,
            Material.LIGHT_BLUE_STAINED_GLASS,
            Material.BLUE_STAINED_GLASS,
            Material.PURPLE_STAINED_GLASS,
            Material.MAGENTA_STAINED_GLASS,
            Material.PINK_STAINED_GLASS);
    private static final int pageCount = optionalBlocks.size() / 45;
    public static Inventory inv;
    public static void initBlockSelectionMenu(int page) {
        inv = Bukkit.createInventory(null, 54, ColorParser.parse("&dBlockin练习菜单-方块选择第" + (page + 1) + "&d页"));
        for (int i = 0; i < 45; i++) {
            if (page * 45 + i >= optionalBlocks.size()) break;
            else
                inv.setItem(i, new ItemStackFactory(optionalBlocks.get(page * 45 + i)).toItemStack());
        }
        inv.setItem(45, new ItemStackFactory(Material.ARROW)
                .setDisplayName("&d上一页")
                .addLore("&6点击以查看上一页")
                .toItemStack());
        inv.setItem(49, new ItemStackFactory(Material.BARRIER)
                .setDisplayName("&c返回")
                .addLore("&6点击以返回上级菜单")
                .toItemStack());
        inv.setItem(53, new ItemStackFactory(Material.ARROW)
                .setDisplayName("&d下一页")
                .addLore("&6点击以查看下一页")
                .toItemStack());
    }

    public static void openBlockSelectionMenu(Player player, int page) {
        initBlockSelectionMenu(page);
        player.openInventory(inv);
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv)) {
                    for (int i = 0; i < 45; i++) {
                        if (e.getCurrentItem() != null && e.getSlot() == i) {
                            ConfigManager.getDataConfig().set("Data." + e.getWhoClicked().getName() + ".Block", new ItemStackFactory(optionalBlocks.get(page * 45 + i)).toItemStack());
                            ConfigManager.saveConfig();
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            Tools.updatePlayerBlocks(player);
                        }
                    } switch (e.getSlot()) {
                        case 45:
                            if (page != 0) {
                                HandlerList.unregisterAll(this);
                                BlockSelectionMenu.openBlockSelectionMenu(player, page - 1);
                            } break;
                        case 49:
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            break;
                        case 53:
                            if (page == pageCount - 1) {
                                HandlerList.unregisterAll(this);
                                BlockSelectionMenu.openBlockSelectionMenu(player, page + 1);
                            } break;
                    }
                }
            }
            @EventHandler
            public void onInventoryClose(InventoryCloseEvent e) {
                if (player.equals(e.getPlayer()) && e.getInventory().equals(inv)) {
                    HandlerList.unregisterAll(this);
                }
            }
        }, Main.instance);
    }
}