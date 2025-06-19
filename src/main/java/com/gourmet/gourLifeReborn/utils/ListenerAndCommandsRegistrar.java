package com.gourmet.gourLifeReborn.utils;

import com.gourmet.gourLifeReborn.GourLifeReborn;
import com.gourmet.gourLifeReborn.commands.LifeCMD;
import com.gourmet.gourLifeReborn.events.KillEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListenerAndCommandsRegistrar {

    public static void registerAll(){
        registerEvents();
        registerCommands();
    }

    private static void registerCommands(){
        Lamp<BukkitCommandActor> handler = BukkitLamp.builder(GourLifeReborn.getInstance()).build();

        handler.register(
                new LifeCMD()
        );
    }

    private static void registerEvents(){
        PluginManager pm = GourLifeReborn.getInstance().getServer().getPluginManager();

        List<Listener> listeners = List.of(
                new KillEvent()
        );

        listeners.forEach(listener -> pm.registerEvents(listener, GourLifeReborn.getInstance()));
    }

}
