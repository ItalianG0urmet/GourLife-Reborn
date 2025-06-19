package com.gourmet.gourLifeReborn;

import com.gourmet.gourLifeReborn.database.DatabaseCredential;
import com.gourmet.gourLifeReborn.database.DatabaseMySQL;
import com.gourmet.gourLifeReborn.utils.ListenerRegistrar;
import com.gourmet.gourLifeReborn.utils.Logger;
import com.gourmet.gourLifeReborn.utils.config.LanguageConfigManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GourLifeReborn extends JavaPlugin {

    @Getter private static GourLifeReborn instance;

    @Override
    public void onEnable() {
        instance = this;

        DatabaseMySQL.getInstance().initDatabase();
        if (!DatabaseMySQL.getInstance().isEnabled()){
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        ListenerRegistrar.registerAll();

        Logger.info(" > " + LanguageConfigManager.getInstance().finalDeathMessage);
    }

}
