package com.gourmet.gourLifeReborn.database;

import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public interface IDatabaseSystem {

    void initDatabase();
    void removeLife(Player player, int life);
    void removeLife(Player player);
    void setLives(Player player, int life);
    void addLife(Player player, int life);
    void addLife(Player player);
    CompletableFuture<Integer> getLife(Player player);

}
