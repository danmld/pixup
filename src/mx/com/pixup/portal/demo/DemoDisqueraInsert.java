/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author JAVA-13
 */
public class DemoDisqueraInsert {
    
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        System.out.println("Bienvendido a Pixup");
        System.out.println("Mantenimiento de catálogo Disquera");
        System.out.println("Por favor ingrese la disquera que se quiere insertar: ");
        
        Connection connection = null;
        Statement statement = null;
        try {
            String nombreDisquera = br.readLine();
            
            // Cargando Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establecer conexion
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pixup", "root", "admin");
            
            // Crear objeto para interactuar con base de datos
            statement = connection.createStatement();
            String sql = 
                "insert into disquera (nombre) values ('" 
                + nombreDisquera + "')";
            
            // Ejecutar sentencia sql
            statement.execute(sql);
            
            System.out.println("La disquera: " + nombreDisquera + 
                    " fue insertada de forma exitosa!!!!");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No disponible, intente más tarde!!!");
        } finally {
            // Liberar recursos
            if(statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(connection != null) {
               try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }        
    }
        
}
