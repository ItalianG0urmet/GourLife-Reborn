package com.gourmet.gourLifeReborn.events;

import com.gourmet.gourLifeReborn.database.DatabaseMySQL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillEvent implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        var player = e.getPlayer();
        var killer = player.getKiller();

        if(killer == null) return;

        var databaseManager = DatabaseMySQL.getInstance();

        databaseManager.addLife(killer);
        databaseManager.removeLife(player);

    }

}
