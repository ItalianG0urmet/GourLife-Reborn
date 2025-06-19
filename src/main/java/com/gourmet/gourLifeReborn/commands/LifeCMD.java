package com.gourmet.gourLifeReborn.commands;

import com.gourmet.gourLifeReborn.database.DatabaseMySQL;
import com.gourmet.gourLifeReborn.utils.Utils;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;

@Command("glife")
public class LifeCMD {

    private final DatabaseMySQL database = DatabaseMySQL.getInstance();

    @Subcommand("check <target>")
    public void checkCommand(Player player, Player target){

        if(target == null){
            player.sendMessage(Utils.toMini("Target don't exist"));
            return;
        }

        database.getLife(target).thenAccept(lives -> {
             player.sendMessage(Utils.toMini("Player lives: " + lives));
        });
    }

    @Subcommand("check")
    public void checkCommand(Player player){
        database.getLife(player).thenAccept(lives -> {
            player.sendMessage(Utils.toMini("Your lives: " + lives));
        });

    }

    @Subcommand("set <target> <amount>")
    public void setCommand(Player player, Player target, int amount){

        if(target == null){
            player.sendMessage(Utils.toMini("Target don't exist"));
            return;
        }

        database.setLives(target, amount);

        player.sendMessage("New life set");

    }
}
