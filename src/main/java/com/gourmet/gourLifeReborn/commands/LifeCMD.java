package com.gourmet.gourLifeReborn.commands;

import com.gourmet.gourLifeReborn.database.DatabaseCredential;
import com.gourmet.gourLifeReborn.database.DatabaseMySQL;
import com.gourmet.gourLifeReborn.utils.Utils;
import com.gourmet.gourLifeReborn.utils.config.DefaultConfigManager;
import com.gourmet.gourLifeReborn.utils.config.LanguageConfigManager;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command("glife")
public class LifeCMD {

    private final DatabaseMySQL database = DatabaseMySQL.getInstance();
    private final LanguageConfigManager lang = LanguageConfigManager.getInstance();

    @Subcommand("check <target>")
    public void checkOtherCommand(Player player, Player target) {
        if (target == null) {
            player.sendMessage(Utils.toMini(lang.noPlayer));
            return;
        }

        database.getLife(target).thenAccept(lives -> {
            player.sendMessage(Utils.toMini(
                    lang.checkLives
                            .replace("%player%", target.getName())
                            .replace("%lives%", lives.toString())
            ));
        });
    }

    @Subcommand("check")
    public void checkSelfCommand(Player player) {
        database.getLife(player).thenAccept(lives -> {
            player.sendMessage(Utils.toMini(
                    lang.yourLives
                            .replace("%lives%", lives.toString())
            ));
        });
    }

    @Subcommand("set <target> <amount>")
    @CommandPermission("glife.admin")
    public void setCommand(Player player, Player target, int amount) {
        if (target == null) {
            player.sendMessage(Utils.toMini(lang.cantFindPlayer));
            return;
        }

        database.setLives(target, amount);
        player.sendMessage(Utils.toMini(
                lang.setLives.
                        replace("%new_life%", String.valueOf(amount))
        ));
    }

    @Subcommand("reload")
    @CommandPermission("glife.admin")
    public void setReload(Player player){
        LanguageConfigManager.getInstance().reloadConfig();
        DefaultConfigManager.getInstance().reloadConfig();
    }

}