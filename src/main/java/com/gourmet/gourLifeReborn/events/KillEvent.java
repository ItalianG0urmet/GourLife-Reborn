package com.gourmet.gourLifeReborn.events;

import com.gourmet.gourLifeReborn.database.DatabaseManager;
import com.gourmet.gourLifeReborn.database.DatabaseMySQL;
import com.gourmet.gourLifeReborn.database.DatabaseSqlLite;
import com.gourmet.gourLifeReborn.utils.Utils;
import com.gourmet.gourLifeReborn.utils.config.LanguageConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillEvent implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        var data = DatabaseManager.getInstance().getDatabaseSystem();
        var lang = LanguageConfigManager.getInstance();
        var player = e.getPlayer();
        var killer = player.getKiller();

        e.setDeathMessage("");

        if (killer == null) {
            data.removeLife(player).thenCompose(v ->
                    data.getLife(player)
            ).thenAccept(newLives -> {
                var message = lang.deathMessage
                        .replace("%player%", player.getName())
                        .replace("%lives%", String.valueOf(newLives));

                Utils.sendMessageAll(message);

                if (newLives <= 0) {
                    finalDeath();
                }
            });
        } else {

            data.removeLife(player).thenCompose(v ->
                    data.addLife(killer)
            ).thenCompose(v ->
                    data.getLife(player)
            ).thenAccept(newLives -> {
                var message = lang.deathByPlayerMessage
                        .replace("%player%", player.getName())
                        .replace("%lives%", String.valueOf(newLives))
                        .replace("%killer%", killer.getName());

                Utils.sendMessageAll(message);

                if (newLives <= 0) {
                    finalDeath();
                }
            });

        }

    }

    // TODO: ban o final kill
    private void finalDeath(){

    }

}
