package com.gourmet.gourLifeReborn.utils.config;

import com.gourmet.gourLifeReborn.GourLifeReborn;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageConfigManager implements IConfigManager {

    private File file;
    private FileConfiguration config;
    private final GourLifeReborn gourLifeReborn = GourLifeReborn.getInstance();

    private LanguageConfigManager(){
        initCustomConfig();
        loadConfig();
    }

    /* var definition */
    public String deathMessage;
    public String finalDeathMessage;

    @Override
    public void loadConfig() {

        deathMessage = config.getString("messages.death-message");
        finalDeathMessage = config.getString("messages.final-death-message");
    }

    @Override
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    private void initCustomConfig(){
        file = new File(gourLifeReborn.getDataFolder(), "language.yml");

        if(!file.exists()){
            gourLifeReborn.saveResource("language.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }


    /* Singletone define */
    private static class Holder {
        private static final LanguageConfigManager instance = new LanguageConfigManager();
    }

    public static LanguageConfigManager getInstance(){
        return LanguageConfigManager.Holder.instance;
    }
}
