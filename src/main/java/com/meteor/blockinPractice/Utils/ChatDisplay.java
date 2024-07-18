package com.meteor.blockinPractice.Utils;

import org.bukkit.entity.Player;

public class ChatDisplay {
    public static void help(Player player) {
        player.sendMessage(
                "§b§l⭐§e§lBlockinPractice§b§l⭐\n",
                "§7/§bbpc §7addIsland <islandName> ➔ §9添加一个岛屿\n",
                "§7/§bbpc §7editMode ➔ §9切换编辑模式\n",
                "§7/§bbpc §7help ➔ §9显示此帮助\n",
                "§7/§bbpc §7setBed <islandId> ➔ §9设置床位置(站在红色床上设置)\n",
                "§7/§bbpc §7setSpawn ➔ §9设置世界出生点\n",
                "§7/§bbpc §7setWaypoint <islandId> ➔ §9设置传送点\n",
                "§ctips: 此插件仅限主世界使用, 请不要关闭主世界生成");
    }

    public static void outTime(Player player) {
        player.sendMessage("§c§l失败, 因为你超出了规定时间的120s");
    }

    public static void outWorld(Player player) {
        player.sendMessage("§c§l失败, 因为你超出了世界边界");
    }

    public static void invalidBlockin(Player player) {
        player.sendMessage("§c§l失败, 因为你的Blockin是无效的.");
    }

    public static void islandOccupied(Player player) {
        player.sendMessage("§c§l此岛屿已被占用.");
    }
}
