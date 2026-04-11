package com.uniajc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConexionPostgresDatabase {

    private static Connection connection = null;

    public static Connection getConnection() {
        // Usamos un objeto Properties para cargar los parámetros de conexión desde un archivo de configuración
        Properties properties = new Properties();
        if (connection == null) {
            try {
                // Cargar las propiedades desde el archivo config-postgres.properties
                properties.load(new FileInputStream(new File("config.properties")));

                // Definir los parámetros de conexión
                String url = properties.getProperty("db.url");
                
                System.out.println("URL de conexión: " + url); // Imprime la URL para verificar que se está leyendo correctamente

                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                
                // Establecer la conexión
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión a base de datos exitosa.");
            } catch (SQLException error) {
                // System.out.println("Failed to establish database connection. " + error.getMessage());
                error.printStackTrace();
            } catch (FileNotFoundException error) {
                error.printStackTrace();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully.");
            } catch (SQLException error) {
                error.printStackTrace();
                System.out.println("Failed to close database connection. " + error.getMessage());
            }
        }
    }

}
