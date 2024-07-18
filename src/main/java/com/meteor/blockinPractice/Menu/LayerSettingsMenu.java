package com.meteor.blockinPractice.Menu;

import com.meteor.blockinPractice.Main;
import com.meteor.blockinPractice.Utils.ColorParser;
import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.ItemStackFactory;
import com.meteor.blockinPractice.Utils.StructureBlocks;
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
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LayerSettingsMenu {
    private static final List<Material> optionalBlocks = List.of(
            Material.GRAY_STAINED_GLASS_PANE,
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
            Material.PINK_STAINED_GLASS,
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
            Material.END_STONE,
            Material.OAK_PLANKS,
            Material.OBSIDIAN);
    public static Inventory inv;
    public static void initLayerSettingsMenu(int page) {
        if (page == 0) {
            inv = Bukkit.createInventory(null, 36, ColorParser.parse("&dBlockin练习菜单-守床结构选择"));
            inv.setItem(11, new ItemStackFactory(Material.STONE)
                    .setDisplayName("&d第一层结构选择")
                    .addLore("&6点击以更改第一层结构")
                    .toItemStack());
            inv.setItem(13, new ItemStackFactory(Material.STONE)
                    .setDisplayName("&d第二层结构选择")
                    .addLore("&6点击以更改第二层结构")
                    .toItemStack());
            inv.setItem(15, new ItemStackFactory(Material.STONE)
                    .setDisplayName("&d第三层结构选择")
                    .addLore("&6点击以更改第三层结构")
                    .toItemStack());
            inv.setItem(31, new ItemStackFactory(Material.BARRIER)
                    .setDisplayName("&c返回")
                    .addLore("&6点击以返回上级菜单")
                    .toItemStack());
        } else {
            inv = Bukkit.createInventory(null, 45, ColorParser.parse("&bBlockin练习菜单-守床结构第" + page + "&b层选择"));
            int i;
            if (page == 1) i = 1;
            else i = 0;
            for (; i <= 35; i++) {
                inv.setItem(i, new ItemStackFactory(optionalBlocks.get(i)).addLore("&7点击选择").toItemStack());
                if (i == 0) inv.setItem(i, new ItemStackFactory(inv.getItem(i)).setDisplayName("&f无方块").toItemStack());
            }
            inv.setItem(40, new ItemStackFactory(Material.BARRIER)
                    .setDisplayName("&c返回")
                    .addLore("&6点击以返回上级菜单")
                    .toItemStack());
        }

    }

    public static void openLayerSettingsMainMenu(Player player) {
        initLayerSettingsMenu(0);
        ItemStack itemType;
        itemType = ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.First");
        if (itemType == null || itemType.getType() == Material.AIR)
            inv.setItem(11, new ItemStackFactory(inv.getItem(11))
                    .setType(Material.GRAY_STAINED_GLASS_PANE)
                    .toItemStack());
        else
            inv.setItem(11, new ItemStackFactory(inv.getItem(11))
                    .setType(itemType.getType())
                    .toItemStack());
        itemType = ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.Second");
        if (itemType == null || itemType.getType() == Material.AIR)
            inv.setItem(13, new ItemStackFactory(inv.getItem(13))
                    .setType(Material.GRAY_STAINED_GLASS_PANE)
                    .toItemStack());
        else
            inv.setItem(13, new ItemStackFactory(inv.getItem(13))
                    .setType(itemType.getType())
                    .toItemStack());
        itemType = ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.Third");
        if (itemType == null || itemType.getType() == Material.AIR)
            inv.setItem(15, new ItemStackFactory(inv.getItem(15))
                    .setType(Material.GRAY_STAINED_GLASS_PANE)
                    .toItemStack());
        else
            inv.setItem(15, new ItemStackFactory(inv.getItem(15))
                    .setType(itemType.getType())
                    .toItemStack());
        player.openInventory(inv);
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv)) {
                    switch (e.getSlot()) {
                        case 11:
                            HandlerList.unregisterAll(this);
                            LayerSettingsMenu.openFirstLayerSettingsSubMenu(player);
                            break;
                        case 13:
                            HandlerList.unregisterAll(this);
                            LayerSettingsMenu.openSecondLayerSettingsSubMenu(player);
                            break;
                        case 15:
                            HandlerList.unregisterAll(this);
                            LayerSettingsMenu.openThirdLayerSettingsSubMenu(player);
                            break;
                        case 31:
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            break;
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

    public static void openFirstLayerSettingsSubMenu(Player player) {
        initLayerSettingsMenu(1);
        player.openInventory(inv);
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv)) {
                    for (int i = 0; i < 36; i++) {
                        if (e.getCurrentItem() != null && e.getSlot() == i) {
                            if (i == 0) ConfigManager.getDataConfig().set("Data." + e.getWhoClicked().getName() + ".Selection.First", new ItemStackFactory(Material.AIR).toItemStack());
                            else ConfigManager.getDataConfig().set("Data." + e.getWhoClicked().getName() + ".Selection.First", new ItemStackFactory(optionalBlocks.get(i)).toItemStack());
                            ConfigManager.saveConfig();
                            HandlerList.unregisterAll(this);
                            LayerSettingsMenu.openLayerSettingsMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            StructureBlocks.placeStructure(player);
                        }
                    } if (e.getSlot() == 40) {
                        HandlerList.unregisterAll(this);
                        LayerSettingsMenu.openLayerSettingsMainMenu(player);
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
    public static void openSecondLayerSettingsSubMenu(Player player) {
        initLayerSettingsMenu(2);
        player.openInventory(inv);
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv)) {
                    for (int i = 0; i < 36; i++) {
                        if (e.getCurrentItem() != null && e.getSlot() == i) {
                            if (i == 0) ConfigManager.getDataConfig().set("Data." + e.getWhoClicked().getName() + ".Selection.Second", new ItemStackFactory(Material.AIR).toItemStack());
                            else ConfigManager.getDataConfig().set("Data." + e.getWhoClicked().getName() + ".Selection.Second", new ItemStackFactory(optionalBlocks.get(i)).toItemStack());
                            ConfigManager.saveConfig();
                            HandlerList.unregisterAll(this);
                            LayerSettingsMenu.openLayerSettingsMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            StructureBlocks.placeStructure(player);
                        }
                    } if (e.getSlot() == 40) {
                        HandlerList.unregisterAll(this);
                        LayerSettingsMenu.openLayerSettingsMainMenu(player);
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
    public static void openThirdLayerSettingsSubMenu(Player player) {
        initLayerSettingsMenu(3);
        player.openInventory(inv);
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv)) {
                    for (int i = 0; i < 36; i++) {
                        if (e.getCurrentItem() != null && e.getSlot() == i) {
                            if (i == 0) ConfigManager.getDataConfig().set("Data." + e.getWhoClicked().getName() + ".Selection.Third", new ItemStackFactory(Material.AIR).toItemStack());
                            else ConfigManager.getDataConfig().set("Data." + e.getWhoClicked().getName() + ".Selection.Third", new ItemStackFactory(optionalBlocks.get(i)).toItemStack());
                            ConfigManager.saveConfig();
                            HandlerList.unregisterAll(this);
                            LayerSettingsMenu.openLayerSettingsMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            StructureBlocks.placeStructure(player);
                        }
                    } if (e.getSlot() == 40) {
                        HandlerList.unregisterAll(this);
                        LayerSettingsMenu.openLayerSettingsMainMenu(player);
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
