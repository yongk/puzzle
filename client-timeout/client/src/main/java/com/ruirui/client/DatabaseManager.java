package com.ruirui.client;

import org.hsqldb.util.DatabaseManagerSwing;

public class DatabaseManager {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        DatabaseManagerSwing.main(new String[] {
                "--url", "jdbc:h2:file:./data/testdb;DB_CLOSE_ON_EXIT=false;DB_CLOSE_DELAY=-1;MODE=MYSQL",
                "--user", "sa",
                "--password", "" });
    }
}
