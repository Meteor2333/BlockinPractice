package com.meteor.blockinPractice.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.meteor.blockinPractice.Main.instance;

public class StructureBlocks {
    private static int[] bedHeadBlocksX;
    private static int[] bedHeadBlocksY;
    private static int[] bedHeadBlocksZ;
    private static int[] bedFootBlocksX;
    private static int[] bedFootBlocksY;
    private static int[] bedFootBlocksZ;
    static int bedHeadX;
    static int bedHeadY;
    static int bedHeadZ;
    static int bedFootX;
    static int bedFootY;
    static int bedFootZ;
    static World world = Bukkit.getWorld("world");

    public static void placeStructure(Player player) {
        clearBlocks(ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island"));
        List<Location> firstLayerBlocks = getFirstLayer(ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island"));
        List<Location> secondLayerBlocks = getSecondLayer(ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island"));
        List<Location> thirdLayerBlocks = getThirdLayer(ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Island"));
        for (Location FirstLayerBlocks : firstLayerBlocks) FirstLayerBlocks.getBlock().setType(Objects.requireNonNull(ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.First")).getType());
        for (Location SecondLayerBlocks : secondLayerBlocks) SecondLayerBlocks.getBlock().setType(Objects.requireNonNull(ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.Second")).getType());
        for (Location ThirdLayerBlocks : thirdLayerBlocks) ThirdLayerBlocks.getBlock().setType(Objects.requireNonNull(ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.Third")).getType());
    }

    public static void clearBlocks(int IslandId) {
        getBedLocation(IslandId);
        Block CleanupBlock;
        for (int x = -4; x <= 4; x++) {
            for (int y = 0; y <= 4; y++) {
                for (int z = -4; z <= 4; z++) {
                    CleanupBlock = world.getBlockAt(bedHeadX, bedHeadY, bedHeadZ).getLocation().add(x, y, z).getBlock();
                    if (CleanupBlock.getType() != Material.RED_BED) CleanupBlock.setType(Material.AIR);
                    CleanupBlock = world.getBlockAt(bedFootX, bedFootY, bedFootZ).getLocation().add(x, y, z).getBlock();
                    if (CleanupBlock.getType() != Material.RED_BED) CleanupBlock.setType(Material.AIR);
                }
            }
        }
    }

    public static List<Location> getFirstLayer(int IslandId) {
        if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "NORTH")) {
            bedHeadBlocksX = new int[]{0, 0, -1, 1};
            bedHeadBlocksY = new int[]{0, 1, 0, 0};
            bedHeadBlocksZ = new int[]{-1, 0, 0, 0};
            bedFootBlocksX = new int[]{0, 0, -1, 1};
            bedFootBlocksY = new int[]{0, 1, 0, 0};
            bedFootBlocksZ = new int[]{1, 0, 0, 0};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "SOUTH")) {
            bedHeadBlocksX = new int[]{0, 0, 1, -1};
            bedHeadBlocksY = new int[]{0, 1, 0, 0};
            bedHeadBlocksZ = new int[]{1, 0, 0, 0};
            bedFootBlocksX = new int[]{0, 0, 1, -1};
            bedFootBlocksY = new int[]{0, 1, 0, 0};
            bedFootBlocksZ = new int[]{-1, 0, 0, 0};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "EAST")) {
            bedHeadBlocksX = new int[]{1, 0, 0, 0};
            bedHeadBlocksY = new int[]{0, 1, 0, 0};
            bedHeadBlocksZ = new int[]{0, 0, -1, 1};
            bedFootBlocksX = new int[]{-1, 0, 0, 0};
            bedFootBlocksY = new int[]{0, 1, 0, 0};
            bedFootBlocksZ = new int[]{0, 0, -1, 1};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "WEST")) {
            bedHeadBlocksX = new int[]{-1, 0, 0, 0};
            bedHeadBlocksY = new int[]{0, 1, 0, 0};
            bedHeadBlocksZ = new int[]{0, 0, -1, 1};
            bedFootBlocksX = new int[]{1, 0, 0, 0};
            bedFootBlocksY = new int[]{0, 1, 0, 0};
            bedFootBlocksZ = new int[]{0, 0, -1, 1};
        } else {
            Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件读取时异常, 可能由于设置错误或手动更改了配置文件");
            Bukkit.getPluginManager().disablePlugin(instance);
        }
        List<Location> BlockLocations = new ArrayList<>(List.of());
        getBedLocation(IslandId);
        for (int i = 0; i < 4; i++)
            BlockLocations.add(world.getBlockAt(bedHeadX + bedHeadBlocksX[i], bedHeadY + bedHeadBlocksY[i], bedHeadZ + bedHeadBlocksZ[i]).getLocation());
        for (int i = 0; i < 4; i++)
            BlockLocations.add(world.getBlockAt(bedFootX + bedFootBlocksX[i], bedFootY + bedFootBlocksY[i], bedFootZ + bedFootBlocksZ[i]).getLocation());
        return BlockLocations;
    }

    public static List<Location> getSecondLayer(int IslandId) {
        if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "NORTH")) {
            bedHeadBlocksX = new int[]{0, 0, 0, -1, 1, -2, -1, 1, 2};
            bedHeadBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedHeadBlocksZ = new int[]{-2, -1, 0, -1, -1, 0, 0, 0, 0};
            bedFootBlocksX = new int[]{0, 0, 0, -1, 1, -2, -1, 1, 2};
            bedFootBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedFootBlocksZ = new int[]{2, 1, 0, 1, 1, 0, 0, 0, 0};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "SOUTH")) {
            bedHeadBlocksX = new int[]{0, 0, 0, 1, -1, 2, 1, -1, -2};
            bedHeadBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedHeadBlocksZ = new int[]{2, 1, 0, 1, 1, 0, 0, 0, 0};
            bedFootBlocksX = new int[]{0, 0, 0, 1, -1, 2, 1, -1, -2};
            bedFootBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedFootBlocksZ = new int[]{-2, -1, 0, -1, -1, 0, 0, 0, 0};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "EAST")) {
            bedHeadBlocksX = new int[]{2, 1, 0, 1, 1, 0, 0, 0, 0};
            bedHeadBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedHeadBlocksZ = new int[]{0, 0, 0, -1, 1, -2, -1, 1, 2};
            bedFootBlocksX = new int[]{-2, -1, 0, -1, -1, 0, 0, 0, 0};
            bedFootBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedFootBlocksZ = new int[]{0, 0, 0, -1, 1, -2, -1, 1, 2};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "WEST")) {
            bedHeadBlocksX = new int[]{-2, -1, 0, -1, -1, 0, 0, 0, 0};
            bedHeadBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedHeadBlocksZ = new int[]{0, 0, 0, 1, -1, 2, 1, -1, -2};
            bedFootBlocksX = new int[]{2, 1, 0, 1, 1, 0, 0, 0, 0};
            bedFootBlocksY = new int[]{0, 1, 2, 0, 0, 0, 1, 1, 0};
            bedFootBlocksZ = new int[]{0, 0, 0, 1, -1, 2, 1, -1, -2};
        } else {
            Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件读取时异常, 可能由于设置错误或手动更改了配置文件");
            Bukkit.getPluginManager().disablePlugin(instance);
        }
        List<Location> BlockLocations = new ArrayList<>(List.of());
        getBedLocation(IslandId);
        for (int i = 0; i < 9; i++)
            BlockLocations.add(world.getBlockAt(bedHeadX + bedHeadBlocksX[i], bedHeadY + bedHeadBlocksY[i], bedHeadZ + bedHeadBlocksZ[i]).getLocation());
        for (int i = 0; i < 9; i++)
            BlockLocations.add(world.getBlockAt(bedFootX + bedFootBlocksX[i], bedFootY + bedFootBlocksY[i], bedFootZ + bedFootBlocksZ[i]).getLocation());
        return BlockLocations;
    }

    public static List<Location> getThirdLayer(int IslandId) {
        if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "NORTH")) {
            bedHeadBlocksX = new int[]{0, 0, 0, 0, -1, 1, -2, -1, 1, 2, -3, -2, -1, 1, 2, 3};
            bedHeadBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedHeadBlocksZ = new int[]{-3, -2, -1, 0, -2, -2, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0};
            bedFootBlocksX = new int[]{0, 0, 0, 0, -1, 1, -2, -1, 1, 2, -3, -2, -1, 1, 2, 3};
            bedFootBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedFootBlocksZ = new int[]{3, 2, 1, 0, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "SOUTH")) {
            bedHeadBlocksX = new int[]{0, 0, 0, 0, 1, -1, 2, 1, -1, -2, 3, 2, 1, -1, -2, -3};
            bedHeadBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedHeadBlocksZ = new int[]{3, 2, 1, 0, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
            bedFootBlocksX = new int[]{0, 0, 0, 0, 1, -1, 2, 1, -1, -2, 3, 2, 1, -1, -2, -3};
            bedFootBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedFootBlocksZ = new int[]{-3, -2, -1, 0, -2, -2, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "EAST")) {
            bedHeadBlocksX = new int[]{3, 2, 1, 0, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
            bedHeadBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedHeadBlocksZ = new int[]{0, 0, 0, 0, -1, 1, -2, -1, 1, 2, -3, -2, -1, 1, 2, 3};
            bedFootBlocksX = new int[]{-3, -2, -1, 0, -2, -2, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0};
            bedFootBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedFootBlocksZ = new int[]{0, 0, 0, 0, -1, 1, -2, -1, 1, 2, -3, -2, -1, 1, 2, 3};
        } else if (Objects.equals(ConfigManager.getIslandConfig().getString("Island." + IslandId + ".Bed.Face"), "WEST")) {
            bedHeadBlocksX = new int[]{-3, -2, -1, 0, -2, -2, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0};
            bedHeadBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedHeadBlocksZ = new int[]{0, 0, 0, 0, 1, -1, 2, 1, -1, -2, 3, 2, 1, -1, -2, -3};
            bedFootBlocksX = new int[]{3, 2, 1, 0, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
            bedFootBlocksY = new int[]{0, 1, 2, 3, 0, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 0};
            bedFootBlocksZ = new int[]{0, 0, 0, 0, 1, -1, 2, 1, -1, -2, 3, 2, 1, -1, -2, -3};
        } else {
            Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件读取时异常, 可能由于设置错误或手动更改了配置文件");
            Bukkit.getPluginManager().disablePlugin(instance);
        }
        List<Location> BlockLocations = new ArrayList<>(List.of());
        getBedLocation(IslandId);
        for (int i = 0; i < 16; i++)
            BlockLocations.add(world.getBlockAt(bedHeadX + bedHeadBlocksX[i], bedHeadY + bedHeadBlocksY[i], bedHeadZ + bedHeadBlocksZ[i]).getLocation());
        for (int i = 0; i < 16; i++)
            BlockLocations.add(world.getBlockAt(bedFootX + bedFootBlocksX[i], bedFootY + bedFootBlocksY[i], bedFootZ + bedFootBlocksZ[i]).getLocation());
        return BlockLocations;
    }
    
    public static void getBedLocation(int IslandId) {
        bedHeadX = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Head")).getBlockX();
        bedHeadY = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Head")).getBlockY();
        bedHeadZ = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Head")).getBlockZ();
        bedFootX = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Foot")).getBlockX();
        bedFootY = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Foot")).getBlockY();
        bedFootZ = Objects.requireNonNull(ConfigManager.getIslandConfig().getLocation("Island." + IslandId + ".Bed.Foot")).getBlockZ();
    }
}
