package io.egg.tickspeed;

import de.themoep.minedown.MineDown;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TickSpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("tickspeed.view") && args.length == 0) {
            sender.spigot().sendMessage(MineDown.parse("&#2ab538&Current target tickrate is &#00c9db&" + (TickSpeed.targetTps == -1 ? "realtime" : TickSpeed.targetTps)));

        } else if (sender.hasPermission("tickspeed.change") && args.length == 1) {
            // change tickspeed
            if (args[0].equalsIgnoreCase("realtime")) {
                TickSpeed.targetTps = -1;
                sender.spigot().sendMessage(MineDown.parse("&#2ab538&Changed target tickrate to &#00c9db&realtime"));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("tickspeed.notify") && p != sender) {
                        sender.spigot().sendMessage(MineDown.parse("&#00c9db&" + sender.getName() + "&#2ab538&: changed target tickrate to realtime"));
                    }
                }
                return true;
            }
            try {
                int h = Integer.parseInt(args[0]);
                TickSpeed.targetTps = h;
                sender.spigot().sendMessage(MineDown.parse("&#2ab538&Changed target tickrate to &#00c9db&" + h));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("tickspeed.notify") && p != sender) {
                        sender.spigot().sendMessage(MineDown.parse("&#00c9db&" + sender.getName() + "&#2ab538&: changed target tickrate to &#00c9db&" + h));
                    }
                }
            } catch(Exception e) {
                sender.spigot().sendMessage(MineDown.parse("&#c92c2c&Unknown command, type /help for a list of commands"));
            }

        } else {
            sender.spigot().sendMessage(MineDown.parse("&#c92c2c&Unknown command, type /help for a list of commands"));
        }
        return true;
    }
}
