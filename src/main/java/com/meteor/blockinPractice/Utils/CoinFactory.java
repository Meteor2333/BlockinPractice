package com.meteor.blockinPractice.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.meteor.blockinPractice.Main.instance;
import static com.meteor.blockinPractice.Main.timing;

public class CoinFactory {
    public static int coinCalculating(Player player, int IslandId) {
        int coins = 0, time = Integer.parseInt(String.format("%.0f", timing[IslandId]));
        if (Objects.requireNonNull(ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.First")).getType() == Material.OBSIDIAN) time -= 5;
        if (Objects.requireNonNull(ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.Second")).getType() == Material.OBSIDIAN) time -= 4;
        if (Objects.requireNonNull(ConfigManager.getDataConfig().getItemStack("Data." + player.getName() + ".Selection.Third")).getType() == Material.OBSIDIAN) time -= 6;
        switch (ConfigManager.getDataConfig().getInt("Data." + player.getName() + ".Tools")) {
            case 1:
                coins = 9 - time;
                break;
            case 2, 3, 5:
                coins = 8 - time;
                break;
            case 4:
                coins = 7 - time;
                break;
            default:
                Bukkit.getConsoleSender().sendMessage("[BlockinPractice] 配置文件读取时异常, 可能由于设置错误或手动更改了配置文件");
                Bukkit.getPluginManager().disablePlugin(instance);
                break;
        } if (coins < 0) coins = 0;
        return coins;
    }
}
