package com.gourmet.gourLifeReborn.database;

import com.gourmet.gourLifeReborn.utils.BukkitAsyncExecutor;
import com.gourmet.gourLifeReborn.utils.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatabaseMySQL implements IDatabaseSystem {

    private final DatabaseCredential credentials = DatabaseCredential.getInstance();
    private final Executor asyncexecutor = new BukkitAsyncExecutor();
    @Getter private boolean isEnabled = false;

    private String getUrl() {
        return "jdbc:mysql://" +
                credentials.getHost() + ":" +
                credentials.getPort() + "/" +
                credentials.getName() +
                "?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
    }

    private String getBaseUrl() {
        return "jdbc:mysql://" +
                credentials.getHost() + ":" +
                credentials.getPort() +
                "/?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
    }

    private String getUser() {
        return credentials.getUser();
    }

    private String getPassword() {
        return credentials.getPassword();
    }

    /* Singleton define */
    private static class Holder {
        private static final DatabaseMySQL instance = new DatabaseMySQL();
    }

    public static DatabaseMySQL getInstance() {
        return Holder.instance;
    }

    /* Query define */
    @Override
    public void initDatabase() {
        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " +
                credentials.getName() +
                " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";

        String createTableQuery = """
                    CREATE TABLE IF NOT EXISTS life_stats (
                    name VARCHAR(255) NOT NULL PRIMARY KEY,
                    lives INT DEFAULT 3
                )
                """;

        try (Connection conn = DriverManager.getConnection(getBaseUrl(), getUser(), getPassword());
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createDatabaseQuery);
            Logger.info("Database '" + credentials.getName() + "' created/verified successfully");
        } catch (SQLException e) {
            Logger.warning("Error creating the database: " + e.getMessage());
            return;
        }

        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableQuery);
            Logger.info("Table 'life_stats' created/verified successfully");
        } catch (SQLException e) {
            Logger.warning("Error creating the table: " + e.getMessage());
            return;
        }

        isEnabled = true;
    }

    private void ensurePlayerEntry(Player player) {
        String insertQuery = "INSERT IGNORE INTO life_stats (name) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, player.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.warning("Error creating entry for " + player.getName() + ": " + e.getMessage());
        }
    }

    @Override
    public CompletableFuture<Integer> getLife(Player player) {
        return CompletableFuture.supplyAsync(() -> {
            ensurePlayerEntry(player);
            String query = "SELECT lives FROM life_stats WHERE name = ?";
            int lives = 0;
            try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, player.getName());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        lives = rs.getInt("lives");
                    }
                }
            } catch (SQLException e) {
                Logger.warning("Error retrieving lives for " + player.getName() + ": " + e.getMessage());
            }
            return lives;
        });
    }
    @Override
    public CompletableFuture<Void> addLife(Player player, int life) {
        return CompletableFuture.runAsync(() -> {
            ensurePlayerEntry(player);
            String query = "UPDATE life_stats SET lives = lives + ? WHERE name = ?";
            try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, life);
                stmt.setString(2, player.getName());
                stmt.executeUpdate();
            } catch (SQLException e) {
                Logger.warning("Error adding " + life + " lives to " + player.getName() + ": " + e.getMessage());
            }
        }, asyncexecutor);
    }

    @Override
    public CompletableFuture<Void> addLife(Player player) {
        return addLife(player, 1);
    }

    @Override
    public CompletableFuture<Void> setLives(Player player, int life) {
        return CompletableFuture.runAsync(() -> {
            ensurePlayerEntry(player);
            String query = "UPDATE life_stats SET lives = ? WHERE name = ?";
            try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, life);
                stmt.setString(2, player.getName());
                stmt.executeUpdate();
            } catch (SQLException e) {
                Logger.warning("Error setting lives of " + player.getName() + " to " + life + ": " + e.getMessage());
            }
        }, asyncexecutor);
    }

    @Override
    public CompletableFuture<Void> removeLife(Player player, int life) {
        return CompletableFuture.runAsync(() -> {
            ensurePlayerEntry(player);
            String query = "UPDATE life_stats SET lives = GREATEST(lives - ?, 0) WHERE name = ?";
            try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, life);
                stmt.setString(2, player.getName());
                stmt.executeUpdate();
            } catch (SQLException e) {
                Logger.warning("Error removing " + life + " lives from " + player.getName() + ": " + e.getMessage());
            }
        }, asyncexecutor);
    }

    @Override
    public CompletableFuture<Void> removeLife(Player player) {
        return removeLife(player, 1);
    }

}
