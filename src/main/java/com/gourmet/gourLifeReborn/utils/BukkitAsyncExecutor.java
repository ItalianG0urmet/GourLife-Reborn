package com.gourmet.gourLifeReborn.utils;

import com.gourmet.gourLifeReborn.GourLifeReborn;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class BukkitAsyncExecutor implements Executor {

    public BukkitAsyncExecutor() {}

    @Override
    public void execute(@NotNull Runnable command) {
        Bukkit.getScheduler().runTaskAsynchronously(GourLifeReborn.getInstance(), command);
    }
}
