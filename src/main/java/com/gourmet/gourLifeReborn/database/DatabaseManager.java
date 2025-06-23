package com.gourmet.gourLifeReborn.database;


import com.gourmet.gourLifeReborn.utils.config.DefaultConfigManager;

public class DatabaseManager {

    private IDatabaseSystem iDatabaseSystem;

    private DatabaseManager(){

        if(DefaultConfigManager.getInstance().isSqlLite){
            iDatabaseSystem = DatabaseSqlLite.getInstance();
        } else {
            iDatabaseSystem = DatabaseMySQL.getInstance();
        }

    }

    private static class Holder {
        private static final DatabaseManager instance = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return DatabaseManager.Holder.instance;
    }

    public IDatabaseSystem getDatabaseSystem() {
        return iDatabaseSystem;
    }
}
