package com.meteor.blockinPractice.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.meteor.blockinPractice.Main.instance;

public class Tools {
    public static void givePlayerTools(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemStackFactory(Material.IRON_SWORD)
                .setDisplayName("&c重置并返回传送点")
                .addLore("&7右键点击以重置并返回传送点")
                .setUnbreakable(true)
                .toItemStack());
        updatePlayerBlocks(player);
        switch (ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Tools")) {
            case 1:
                player.getInventory().setItem(2, new ItemStackFactory(Material.WOODEN_PICKAXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b镐子")
                        .setUnbreakable(true)
                        .toItemStack());
                player.getInventory().setItem(3, new ItemStackFactory(Material.WOODEN_AXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b斧子")
                        .setUnbreakable(true)
                        .toItemStack());
                break;
            case 2:
                player.getInventory().setItem(2, new ItemStackFactory(Material.STONE_PICKAXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b镐子")
                        .setUnbreakable(true)
                        .toItemStack());
                player.getInventory().setItem(3, new ItemStackFactory(Material.STONE_AXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b斧子")
                        .setUnbreakable(true)
                        .toItemStack());
                break;
            case 3:
                player.getInventory().setItem(2, new ItemStackFactory(Material.IRON_PICKAXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b镐子")
                        .setUnbreakable(true)
                        .toItemStack());
                player.getInventory().setItem(3, new ItemStackFactory(Material.IRON_AXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b斧子")
                        .setUnbreakable(true)
                        .toItemStack());
                break;
            case 4:
                player.getInventory().setItem(2, new ItemStackFactory(Material.GOLDEN_PICKAXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b镐子")
                        .setUnbreakable(true)
                        .toItemStack());
                player.getInventory().setItem(3, new ItemStackFactory(Material.GOLDEN_AXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b斧子")
                        .setUnbreakable(true)
                        .toItemStack());
                break;
            case 5:
                player.getInventory().setItem(2, new ItemStackFactory(Material.DIAMOND_PICKAXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b镐子")
                        .setUnbreakable(true)
                        .toItemStack());
                player.getInventory().setItem(3, new ItemStackFactory(Material.DIAMOND_AXE)
                        .addEnchant(Enchantment.DIG_SPEED, 2, false)
                        .setDisplayName("&b斧子")
                        .setUnbreakable(true)
                        .toItemStack());
                break;
            default:
                Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件读取时异常, 可能由于设置错误或手动更改了配置文件");
                Bukkit.getPluginManager().disablePlugin(instance);
                break;
        } player.getInventory().setItem(4, new ItemStackFactory(Material.SHEARS)
                .setDisplayName("&b剪刀")
                .setUnbreakable(true)
                .toItemStack());
        player.getInventory().setItem(8, new ItemStackFactory(Material.NETHER_STAR)
                .setDisplayName("&b菜单")
                .addLore("&7右键点击以打开菜单")
                .toItemStack());
    }

    public static void updatePlayerBlocks(Player player) {
        player.getInventory().setItem(1, new ItemStackFactory(Objects.requireNonNull(ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Block")).getType(), 64)
                .setDisplayName("&b方块")
                .toItemStack());
    }
}
