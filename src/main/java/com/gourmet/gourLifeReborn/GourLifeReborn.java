package com.gourmet.gourLifeReborn;

import com.gourmet.gourLifeReborn.database.DatabaseMySQL;
import com.gourmet.gourLifeReborn.external.GourPlaceHolderAPI;
import com.gourmet.gourLifeReborn.utils.ListenerAndCommandsRegistrar;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GourLifeReborn extends JavaPlugin {

    @Getter private static GourLifeReborn instance;

    @Override
    public void onEnable() {
        instance = this;

        //Database
        DatabaseMySQL.getInstance().initDatabase();
        if (!DatabaseMySQL.getInstance().isEnabled()){
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        ListenerAndCommandsRegistrar.registerAll();
        placeHolderInit();
    }

    private void placeHolderInit() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new GourPlaceHolderAPI().register();
        }
    }


}
