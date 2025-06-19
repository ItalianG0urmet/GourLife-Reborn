package com.gourmet.gourLifeReborn.external;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GourPlaceHolderAPI extends PlaceholderExpansion {

    @Override
    @NotNull
    public String getAuthor() {
        return "Gourmet";
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "gourlife";
    }

    @Override
    @NotNull
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        return "";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        return "";
    }

}
