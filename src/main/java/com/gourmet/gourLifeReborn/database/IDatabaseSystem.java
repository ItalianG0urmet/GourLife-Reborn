package com.gourmet.gourLifeReborn.database;

import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public interface IDatabaseSystem {

    void initDatabase();
    CompletableFuture<Void> removeLife(Player player, int life);
    CompletableFuture<Void> removeLife(Player player);
    CompletableFuture<Void> setLives(Player player, int life);
    CompletableFuture<Void> addLife(Player player, int life);
    CompletableFuture<Void> addLife(Player player);
    CompletableFuture<Integer> getLife(Player player);

}
