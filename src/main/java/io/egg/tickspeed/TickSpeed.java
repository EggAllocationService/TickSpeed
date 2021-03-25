package io.egg.tickspeed;

import org.bukkit.plugin.java.JavaPlugin;

public class TickSpeed extends JavaPlugin {
    public static int targetTps = 20;
    @Override
    public void onEnable() {
        getLogger().info("Registering commands");
        getCommand("tickspeed").setExecutor(new TickSpeedCommand());
        getLogger().info("Registering Events");

    }
}
