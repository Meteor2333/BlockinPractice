package com.meteor.blockinPractice.Menu;

import com.meteor.blockinPractice.Main;
import com.meteor.blockinPractice.Utils.ColorParser;
import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.ItemStackFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import static com.meteor.blockinPractice.Main.instance;

public class MainMenu implements Listener {
    public static Inventory inv;
    public static void initMainMenu(Player player) {
        inv = Bukkit.createInventory(null, 36, ColorParser.parse("&dBlockin练习菜单"));
        inv.setItem(10, new ItemStackFactory(Material.SANDSTONE)
                .setDisplayName("&b方块选择")
                .addLore("&6点击以更改方块")
                .toItemStack());
        inv.setItem(12, new ItemStackFactory(Material.PUFFERFISH)
                .setDisplayName("&b岛屿选择")
                .addLore("&6点击以更改岛屿")
                .addLore("")
                .addLore("&a当前岛屿编号: " + ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island"))
                .toItemStack());
        switch (ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Tools")) {
            case 1:
                inv.setItem(14, new ItemStackFactory(Material.WOODEN_PICKAXE)
                        .setDisplayName("&b工具选择")
                        .addLore("&6点击以更改工具")
                        .addLore("")
                        .addLore("&a当前工具: &7木制")
                        .toItemStack());
                break;
            case 2:
                inv.setItem(14, new ItemStackFactory(Material.STONE_PICKAXE)
                        .setDisplayName("&b工具选择")
                        .addLore("&6点击以更改工具")
                        .addLore("")
                        .addLore("&a当前工具: &7石制")
                        .toItemStack());
                break;
            case 3:
                inv.setItem(14, new ItemStackFactory(Material.IRON_PICKAXE)
                        .setDisplayName("&b工具选择")
                        .addLore("&6点击以更改工具")
                        .addLore("")
                        .addLore("&a当前工具: &7铁制")
                        .toItemStack());
                break;
            case 4:
                inv.setItem(14, new ItemStackFactory(Material.GOLDEN_PICKAXE)
                        .setDisplayName("&b工具选择")
                        .addLore("&6点击以更改工具")
                        .addLore("")
                        .addLore("&a当前工具: &7金制")
                        .toItemStack());
                break;
            case 5:
                inv.setItem(14, new ItemStackFactory(Material.DIAMOND_PICKAXE)
                        .setDisplayName("&b工具选择")
                        .addLore("&6点击以更改工具")
                        .addLore("")
                        .addLore("&a当前工具: &7钻石制")
                        .toItemStack());
                break;
            default:
                Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件读取时异常, 可能由于设置错误或手动更改了配置文件");
                Bukkit.getPluginManager().disablePlugin(instance);
                break;
        }
        inv.setItem(16, new ItemStackFactory(Material.BOOK)
                .setDisplayName("&b守床结构选择")
                .addLore("&6点击以更改守床结构")
                .toItemStack());
        inv.setItem(31, new ItemStackFactory(Material.BARRIER)
                .setDisplayName("&c关闭")
                .addLore("&6点击以关闭此菜单")
                .toItemStack());
    }
    public static void openMainMenu(Player player) {
        initMainMenu(player);
        player.openInventory(inv);
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (player.equals(e.getWhoClicked()) && e.getInventory().equals(inv)) {
                    switch (e.getSlot()) {
                        case 10:
                            BlockSelectionMenu.openBlockSelectionMenu(player, 0);
                            break;
                        case 12:
                            IslandSelectionMenu.openIslandSelectionMenu(player);
                            break;
                        case 14:
                            ToolSelectionMenu.openToolSelectionMenu(player);
                            break;
                        case 16:
                            LayerSettingsMenu.openLayerSettingsMainMenu(player);
                            break;
                        case 31:
                            player.closeInventory();
                            HandlerList.unregisterAll(this);
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
}