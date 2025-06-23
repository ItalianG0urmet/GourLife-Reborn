package com.gourmet.gourLifeReborn.database;

import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class DatabaseSqlLite implements IDatabaseSystem{
    @Override
    public void initDatabase() {

    }

    @Override
    public CompletableFuture<Void> removeLife(Player player, int life) {
        return null;
    }

    @Override
    public CompletableFuture<Void> removeLife(Player player) {
        return null;
    }

    @Override
    public CompletableFuture<Void> setLives(Player player, int life) {
        return null;
    }

    @Override
    public CompletableFuture<Void> addLife(Player player, int life) {
        return null;
    }

    @Override
    public CompletableFuture<Void> addLife(Player player) {
        return null;
    }

    @Override
    public CompletableFuture<Integer> getLife(Player player) {
        return null;
    }

    /* Singleton define */
    private static class Holder {
        private static final DatabaseSqlLite instance = new DatabaseSqlLite();
    }

    public static DatabaseSqlLite getInstance() {
        return DatabaseSqlLite.Holder.instance;
    }
}
