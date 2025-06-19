package com.gourmet.gourLifeReborn.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class Logger {


    private static final String PREFIX = "<light_purple>[GourLife]</light_purple> ";

    public static void info(String text) {
        sendText("<white>" + text + "</white>");
    }

    public static void warning(String text) {
        sendText("<red>" + text + "</red>");
    }

    private static void sendText(String text) {
        Component message = Utils.toMini(PREFIX + text);
        Bukkit.getConsoleSender().sendMessage(message);
    }

}
