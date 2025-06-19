package com.gourmet.gourLifeReborn.utils;

import com.gourmet.gourLifeReborn.GourLifeReborn;
import com.gourmet.gourLifeReborn.events.KillEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListenerRegistrar {

    public static void registerAll(){
        PluginManager pm = GourLifeReborn.getInstance().getServer().getPluginManager();

        List<Listener> listeners = List.of(
                new KillEvent()
        );

        listeners.forEach(listener -> pm.registerEvents(listener, GourLifeReborn.getInstance()));
    }

}
