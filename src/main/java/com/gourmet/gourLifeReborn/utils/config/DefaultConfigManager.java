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

    //Database
    public boolean isSqlLite;
    public String dbHost;
    public int dbPort;
    public String dbName;
    public String dbUser;
    public String dbPassword;

    //Settings
    public boolean joinMessages;
    public boolean leaveMessages;
    public boolean deathMessage;
    public boolean finalMessage;
    public int lifeNumber;

    @Override
    public void loadConfig() {
        //Database
        this.isSqlLite = config.getBoolean("database.is-my-sql");
        this.dbHost = config.getString("database.host");
        this.dbPort = config.getInt("database.port");
        this.dbName = config.getString("database.name");
        this.dbUser = config.getString("database.user");
        this.dbPassword = config.getString("database.password");

        //Settings
        this.joinMessages = config.getBoolean("settings.Join-messages");
        this.leaveMessages = config.getBoolean("settings.leave-messages");
        this.deathMessage = config.getBoolean("settings.death-message");
        this.finalMessage = config.getBoolean("settings.final-message");
        this.lifeNumber = config.getInt("settings.life-number");
    }

    @Override
    public void reloadConfig() {
        gourLifeReborn.reloadConfig();
        config = gourLifeReborn.getConfig();
        loadConfig();
    }

    // Singleton
    private static class Holder {
        private static final DefaultConfigManager instance = new DefaultConfigManager();
    }

    public static DefaultConfigManager getInstance() {
        return Holder.instance;
    }
}