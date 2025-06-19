package com.gourmet.gourLifeReborn.utils.config;

import com.gourmet.gourLifeReborn.GourLifeReborn;
import org.bukkit.configuration.file.FileConfiguration;

public class DefaultConfigManager implements IConfigManager {

    private final GourLifeReborn gourLifeReborn = GourLifeReborn.getInstance();
    private FileConfiguration config;

    private DefaultConfigManager() {
        gourLifeReborn.saveDefaultConfig();
        config = gourLifeReborn.getConfig();
        loadConfig();
    }

    /* Variabili database */
    public String dbHost;
    public int dbPort;
    public String dbName;
    public String dbUser;
    public String dbPassword;

    @Override
    public void loadConfig() {
        this.dbHost = config.getString("database.host");
        this.dbPort = config.getInt("database.port");
        this.dbName = config.getString("database.name");
        this.dbUser = config.getString("database.user");
        this.dbPassword = config.getString("database.password");
    }

    @Override
    public void reloadConfig() {
        gourLifeReborn.reloadConfig();
        config = gourLifeReborn.getConfig();
        loadConfig();
    }

    /* Singleton define */
    private static class Holder {
        private static final DefaultConfigManager instance = new DefaultConfigManager();
    }

    public static DefaultConfigManager getInstance() {
        return Holder.instance;
    }
}
