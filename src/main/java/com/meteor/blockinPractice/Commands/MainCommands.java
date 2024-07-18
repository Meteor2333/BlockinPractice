package com.meteor.blockinPractice.Commands;

import com.meteor.blockinPractice.Commands.SubCommands.*;
import com.meteor.blockinPractice.Utils.ConfigManager;
import com.meteor.blockinPractice.Utils.ChatDisplay;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.meteor.blockinPractice.Main.instance;

public class MainCommands implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("§b§lBlockinPractice §7§l>> §c此命令用于配置插件参数, 仅玩家可以执行.");
            return true;
        } else if (!commandSender.isOp()) return true;
        else if (args.length == 0) return false;
        else if (((Player) commandSender).getWorld() != Bukkit.getWorld("world")) {
            Bukkit.getConsoleSender().sendMessage("此插件仅限主世界使用, 请不要关闭主世界生成, 插件已卸载");
            Bukkit.getPluginManager().disablePlugin(instance);
        }
        switch (args[0]) {
            case "addIsland":
                if (args.length == 2) AddIslandCommand.command((Player) commandSender, args[1]);
                break;
            case "editMode":
                EditModeCommand.command((Player) commandSender);
                break;
            case "setBed":
                if (args.length == 2) {
                    if (Integer.parseInt(args[1]) >= instance.getConfig().getInt("Config.IslandAmount")) {
                        commandSender.sendMessage("§b§lBlockinPractice §7§l>> §c岛屿不存在.");
                    } else SetBedCommand.command((Player) commandSender, args[1]);
                } break;
            case "setSpawn":
                SetSpawnCommand.command((Player) commandSender);
                break;

            case "setWaypoint":
                if (ConfigManager.getIslandConfig().get("Island." + args[1]) != null && args.length == 2)
                    SetWaypointCommand.command((Player) commandSender, args[1]);
                else commandSender.sendMessage("§b§lBlockinPractice §7§l>> §c岛屿不存在.");
                break;
            default: ChatDisplay.help((Player) commandSender);
        } return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player || !commandSender.isOp()) {
            List<String> completeList;
            if (args.length == 1) {
                completeList = new ArrayList<>(Arrays.asList("addIsland", "editMode", "help", "setBed", "setSpawn", "setWaypoint"));
                completeList.removeIf(filter -> !filter.toLowerCase().startsWith(args[0].toLowerCase()));
                return completeList;
            } else completeList = List.of();
            return completeList;
        } return null;
    }
}
