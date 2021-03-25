package io.egg.tickspeed;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TickSpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("tickspeed.view") && args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCurrent target tickrate is &b" + (TickSpeed.targetTps == -1 ? "realtime" : TickSpeed.targetTps)));

        } else if (sender.hasPermission("tickspeed.change") && args.length == 1) {
            // change tickspeed
            if (args[0].equalsIgnoreCase("realtime")) {
                TickSpeed.targetTps = -1;
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aChanged target tps to &brealtime"));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("tickspeed.notify") && p != sender) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + sender.getName() + "&a: changed target tickrate to &brealtime"));
                    }
                }
                return true;
            }
            try {
                int h = Integer.parseInt(args[0]);
                TickSpeed.targetTps = h;
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aChanged target tickrate to &b" + h));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("tickspeed.notify") && p != sender) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + sender.getName() + "&a: changed target tickrate to &b" + h));
                    }
                }
            } catch(Exception e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUnknown command, type /help for a list of commands"));
            }

        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUnknown command, type /help for a list of commands"));
        }
        return true;
    }
}
