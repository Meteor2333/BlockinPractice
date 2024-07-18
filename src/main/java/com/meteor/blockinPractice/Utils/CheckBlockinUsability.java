package com.meteor.blockinPractice.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Objects;

public class CheckBlockinUsability {
    public static boolean checkBlockin(Block block) {
        int x = block.getLocation().getBlock().getX(),
            y = block.getLocation().getBlock().getY(),
            z = block.getLocation().getBlock().getZ(),
            voidAmount = 0;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x + 1, y, z).getType() == Material.AIR) voidAmount++;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x - 1, y, z).getType() == Material.AIR) voidAmount++;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x, y, z + 1).getType() == Material.AIR) voidAmount++;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x, y, z - 1).getType() == Material.AIR) voidAmount++;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x + 1, y + 1, z).getType() == Material.AIR) voidAmount++;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x - 1, y + 1, z).getType() == Material.AIR) voidAmount++;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x, y + 1, z + 1).getType() == Material.AIR) voidAmount++;
        if (Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x, y + 1, z - 1).getType() == Material.AIR) voidAmount++;
        return voidAmount > 1 || Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(x, y + 2, z).getType() == Material.AIR;
    }
}
