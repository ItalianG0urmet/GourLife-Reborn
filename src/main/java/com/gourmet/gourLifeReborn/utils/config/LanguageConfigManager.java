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
    public String prefix;
    public String noPlayer;
    public String resetLife;
    public String noPermission;
    public String checkLifes;
    public String yourLifes;
    public String giveLife;
    public String receiveLife;
    public String notEnoughLifes;
    public String cantFindPlayer;
    public String cantSendToYourself;
    public String revelationMessage;
    public String titleRevelation;
    public String subtitleRevelation;
    public String deathMessage;
    public String finalDeathMessage;
    public String joinMessage;
    public String leaveMessage;
    public String gainedLife;
    public String setLife;
    public String helpCommands;


    @Override
    public void loadConfig() {
        prefix = config.getString("messages.prefix");
        noPlayer = config.getString("messages.no-player");
        resetLife = config.getString("messages.reset-life");
        noPermission = config.getString("messages.no-permission");
        checkLifes = config.getString("messages.check-lifes");
        yourLifes = config.getString("messages.your-lifes");
        giveLife = config.getString("messages.give-life");
        receiveLife = config.getString("messages.receive-life");
        notEnoughLifes = config.getString("messages.not-enough-lifes");
        cantFindPlayer = config.getString("messages.cant-find-player");
        cantSendToYourself = config.getString("messages.cant-send-to-yourself");
        revelationMessage = config.getString("messages.revelation_message");
        titleRevelation = config.getString("messages.title_revelation");
        subtitleRevelation = config.getString("messages.subtitle_revelation");
        deathMessage = config.getString("messages.death");
        finalDeathMessage = config.getString("messages.final-death");
        joinMessage = config.getString("messages.join");
        leaveMessage = config.getString("messages.leave");
        gainedLife = config.getString("messages.gained-life");
        setLife = config.getString("messages.set-life");
        helpCommands = config.getString("messages.help-commands");
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
