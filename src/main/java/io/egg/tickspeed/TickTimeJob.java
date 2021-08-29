package io.egg.tickspeed;

import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TickTimeJob implements Runnable {
    public Class<?> server;
    public Class<?> utils;
    public Method getMillis;
    public Method getServer;
    public Field f;
    public TickTimeJob() {
        super();
        try {
            utils = getNmsClass("SystemUtils");
            getMillis = utils.getDeclaredMethod("getMonotonicMillis");
            server = getNmsClass("server.MinecraftServer");
            getServer = server.getMethod("getServer");
            f = server.getDeclaredField("ao");
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        if (TickSpeed.targetTps == 20) {
            return;
        }

        try {

            long current = (long) getMillis.invoke(null);
            long msPerTick;
            if (TickSpeed.targetTps == -1) {
                msPerTick = 0;
            } else {
                msPerTick = (long) Math.floor(1000 / TickSpeed.targetTps);
            }
            current += msPerTick;
            Object s = getServer.invoke(null);
            f.setAccessible(true);
            f.set(s, current);
            f.setAccessible(false);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

        }
    }
    public Class<?> getNmsClass(String name) throws ClassNotFoundException {
        // explode the Server interface implementation's package name into its components
        String[] array = Bukkit.getServer().getClass().getPackage().getName().split("\\.");

        // pick out the component representing the package version if it's present
        String packageVersion = array.length == 4 ? array[3] + "." : "";

        // construct the qualified class name from the obtained package version
        String qualName = "net.minecraft." + name;

        // simple call to get the Class object
        return Class.forName(qualName);
    }
}