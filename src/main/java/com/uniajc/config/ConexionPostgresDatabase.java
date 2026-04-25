package com.uniajc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionPostgresDatabase {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";
    private static final String DEFAULT_SQLITE_URL = "jdbc:sqlite:practica_mvc.db";

    static {
        try (FileInputStream configuracion = new FileInputStream(new File(CONFIG_FILE))) {
            properties.load(configuracion);
        } catch (IOException e) {
            System.out.println("INFO: No se encontró " + CONFIG_FILE + ". Se usará SQLite local si no se provee otra URL.");
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        if (url != null && !url.trim().isEmpty()) {
            if (user != null && password != null) {
                return DriverManager.getConnection(url, user, password);
            }
            return DriverManager.getConnection(url);
        }

        System.out.println("Usando base de datos SQLite local: " + DEFAULT_SQLITE_URL);
        return DriverManager.getConnection(DEFAULT_SQLITE_URL);
    }
}
