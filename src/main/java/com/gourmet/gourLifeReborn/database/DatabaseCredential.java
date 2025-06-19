package com.gourmet.gourLifeReborn.database;

import com.gourmet.gourLifeReborn.utils.config.DefaultConfigManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseCredential {

    private String host;
    private String port;
    private String name;
    private String user;
    private String password;

    private DatabaseCredential() {

        this.host = DefaultConfigManager.getInstance().dbHost;
        this.port = String.valueOf(DefaultConfigManager.getInstance().dbPort);
        this.name = DefaultConfigManager.getInstance().dbName;
        this.user = DefaultConfigManager.getInstance().dbUser;
        this.password = DefaultConfigManager.getInstance().dbPassword;


    }

    private static class Holder {
        private static final DatabaseCredential instance = new DatabaseCredential();
    }

    public static DatabaseCredential getInstance() {
        return Holder.instance;
    }

}
