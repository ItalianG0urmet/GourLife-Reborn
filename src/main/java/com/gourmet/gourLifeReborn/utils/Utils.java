package com.gourmet.gourLifeReborn.utils;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class Utils {
    private final MiniMessage minimessage = MiniMessage.builder().build();

    public Component toMini(String input) {
        return minimessage.deserialize(input);
    }

    public void sendMessageAll(String message) {
        Component component = toMini(message);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(component);
        }
    }
}
