package com.meteor.blockinPractice.Commands.SubCommands;

import com.meteor.blockinPractice.Utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetBedCommand {
    public static void command(Player player, String islandId) {
        Block playerBlock = player.getLocation().getBlock();
        if (playerBlock.getType() == Material.RED_BED) {
            Bed.Part bedPart = ((Bed) playerBlock.getBlockData()).getPart();
            if (bedPart == Bed.Part.HEAD) {
                ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Head", playerBlock.getLocation());
                if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() + 1, playerBlock.getY(), playerBlock.getZ()).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() + 1, playerBlock.getY(), playerBlock.getZ()).getBlockData())).getPart() == Bed.Part.FOOT) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Foot", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() + 1, playerBlock.getY(), playerBlock.getZ()).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "WEST");
                } else if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() - 1, playerBlock.getY(), playerBlock.getZ()).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() - 1, playerBlock.getY(), playerBlock.getZ()).getBlockData())).getPart() == Bed.Part.FOOT) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Foot", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() - 1, playerBlock.getY(), playerBlock.getZ()).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "EAST");
                } else if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() + 1).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() + 1).getBlockData())).getPart() == Bed.Part.FOOT) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Foot", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() + 1).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "NORTH");
                } else if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() - 1).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() - 1).getBlockData())).getPart() == Bed.Part.FOOT) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Foot", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() - 1).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "SOUTH");
                } else {
                    player.sendMessage("§b§lBlockinPractice §7§l>> §c这个床你确定是人放的(＠_＠;)");
                }
            } else {
                ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Foot", playerBlock.getLocation());
                if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() + 1, playerBlock.getY(), playerBlock.getZ()).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() + 1, playerBlock.getY(), playerBlock.getZ()).getBlockData())).getPart() == Bed.Part.HEAD) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Head", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() + 1, playerBlock.getY(), playerBlock.getZ()).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "EAST");
                } else if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() - 1, playerBlock.getY(), playerBlock.getZ()).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() - 1, playerBlock.getY(), playerBlock.getZ()).getBlockData())).getPart() == Bed.Part.HEAD) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Head", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX() - 1, playerBlock.getY(), playerBlock.getZ()).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "WEST");
                } else if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() + 1).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() + 1).getBlockData())).getPart() == Bed.Part.HEAD) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Head", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() + 1).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "SOUTH");
                } else if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() - 1).getType() == Material.RED_BED && ((Bed) (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() - 1).getBlockData())).getPart() == Bed.Part.HEAD) {
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Head", Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(playerBlock.getX(), playerBlock.getY(), playerBlock.getZ() - 1).getLocation());
                    ConfigManager.getIslandConfig().set("Island." + islandId + ".Bed.Face", "NORTH");
                } else {
                    player.sendMessage("§b§lBlockinPractice §7§l>> §c这个床你确定是人放的(＠_＠;)");
                }
            }
            ConfigManager.saveConfig();
            player.sendMessage("§b§lBlockinPractice §7§l>> §c成功保存岛屿编号为" + islandId + "的床位置.");
        } else {
            player.sendMessage("§b§lBlockinPractice §7§l>> §c请站在红色床头或床尾进行设置.");
        }
    }
}
