package com.meteor.blockinPractice.Menu;

import com.meteor.blockinPractice.Utils.ColorParser;
import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.ItemStackFactory;
import com.meteor.blockinPractice.Utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import static com.meteor.blockinPractice.Main.instance;

public class ToolSelectionMenu {
    public static Inventory inv = null;

    public static void initToolSelectionMenu() {
        inv = Bukkit.createInventory(null, 36, ColorParser.parse("&dBlockin练习菜单-工具选择"));
        for (int i = 1; i <= 7; i++)
            inv.setItem(i, new ItemStackFactory(Material.GRAY_STAINED_GLASS_PANE)
                    .addEnchant(Enchantment.LUCK, 10, true)
                    .setDisplayName(" ")
                    .toItemStack());
        inv.setItem(10, new ItemStackFactory(Material.GRAY_STAINED_GLASS_PANE)
                .addEnchant(Enchantment.LUCK, 10, true)
                .setDisplayName(" ")
                .toItemStack());
        inv.setItem(11, new ItemStackFactory(Material.WOODEN_PICKAXE)
                .setDisplayName("&b木制")
                .addLore("&7点击以选择木制工具")
                .toItemStack());
        inv.setItem(12, new ItemStackFactory(Material.STONE_PICKAXE)
                .setDisplayName("&b石制")
                .addLore("&7点击以选择石制工具")
                .toItemStack());
        inv.setItem(13, new ItemStackFactory(Material.IRON_PICKAXE)
                .setDisplayName("&b铁制")
                .addLore("&7点击以选择铁制工具")
                .toItemStack());
        inv.setItem(14, new ItemStackFactory(Material.GOLDEN_PICKAXE)
                .setDisplayName("&b金制")
                .addLore("&7点击以选择金制工具")
                .toItemStack());
        inv.setItem(15, new ItemStackFactory(Material.DIAMOND_PICKAXE)
                .setDisplayName("&b钻石制")
                .addLore("&7点击以选择钻石制工具")
                .toItemStack());
        inv.setItem(16, new ItemStackFactory(Material.GRAY_STAINED_GLASS_PANE)
                .addEnchant(Enchantment.LUCK, 10, true)
                .setDisplayName(" ")
                .toItemStack());
        for (int i = 19; i <= 25; i++)
            inv.setItem(i, new ItemStackFactory(Material.GRAY_STAINED_GLASS_PANE)
                    .addEnchant(Enchantment.LUCK, 10, true)
                    .setDisplayName(" ")
                    .toItemStack());
        inv.setItem(31, new ItemStackFactory(Material.BARRIER)
                .setDisplayName("&c返回")
                .addLore("&6点击以返回上级菜单")
                .toItemStack());
    }

    public static void openToolSelectionMenu(Player player) {
        initToolSelectionMenu();
        switch (ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Tools")) {
            case 1:
                inv.setItem(11, new ItemStackFactory(inv.getItem(11))
                        .addLore("")
                        .addLore("&a当前选择")
                        .toItemStack());
                break;
            case 2:
                inv.setItem(12, new ItemStackFactory(inv.getItem(12))
                        .addLore("")
                        .addLore("&a当前选择")
                        .toItemStack());
                break;
            case 3:
                inv.setItem(13, new ItemStackFactory(inv.getItem(13))
                        .addLore("")
                        .addLore("&a当前选择")
                        .toItemStack());
                break;
            case 4:
                inv.setItem(14, new ItemStackFactory(inv.getItem(14))
                        .addLore("")
                        .addLore("&a当前选择")
                        .toItemStack());
                break;
            case 5:
                inv.setItem(15, new ItemStackFactory(inv.getItem(15))
                        .addLore("")
                        .addLore("&a当前选择")
                        .toItemStack());
                break;
            default:
                Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件读取时异常, 可能由于设置错误或手动更改了配置文件");
                Bukkit.getPluginManager().disablePlugin(instance);
                break;
        } player.openInventory(inv);
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv)) {
                    switch (e.getSlot()) {
                        case 11:
                            ConfigManager.getDataConfig().set("Data." + player.getName() + ".Tools", 1);
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            Tools.givePlayerTools(player);
                            break;
                        case 12:
                            ConfigManager.getDataConfig().set("Data." + player.getName() + ".Tools", 2);
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            Tools.givePlayerTools(player);
                            break;
                        case 13:
                            ConfigManager.getDataConfig().set("Data." + player.getName() + ".Tools", 3);
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            Tools.givePlayerTools(player);
                            break;
                        case 14:
                            ConfigManager.getDataConfig().set("Data." + player.getName() + ".Tools", 4);
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            Tools.givePlayerTools(player);
                            break;
                        case 15:
                            ConfigManager.getDataConfig().set("Data." + player.getName() + ".Tools", 5);
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
                            MainMenu.openMainMenu(player);
                            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.6F, 1F);
                            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6F, 1F);
                            Tools.givePlayerTools(player);
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
        }, instance);
    }
}
