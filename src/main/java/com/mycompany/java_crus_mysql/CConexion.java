package com.mycompany.java_crus_mysql;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public Connection estableceConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            /* JOptionPane.showMessageDialog(null, "La conexión se ha realizado con éxito");*/
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, error: " + e.toString());
            e.printStackTrace();
        }
        return conectar;
    }
    
    // Método para cerrar la conexión (opcional)
    public void cerrarConexion() {
        if (conectar != null) {
            try {
                conectar.close();
                JOptionPane.showMessageDialog(null, "La conexión se ha cerrado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión, error: " + e.toString());
            }
        }
    }
}