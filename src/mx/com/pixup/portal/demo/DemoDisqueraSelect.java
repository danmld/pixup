/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author JAVA-13
 */
public class DemoDisqueraSelect {
    
    public static void main(String[] args) {
        System.out.println("BIENVENIDO A PIXUP");
        System.out.println("Mantenimiento catálogo disquera");
        System.out.println("Listado de disqueras");
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("admin");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/pixup");
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            
            String sql = "select * from disquera order by nombre desc";
            
            resultSet = statement.executeQuery(sql);
            
            System.out.println("ID: \t NOMBRE:");
            while(resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                System.out.println(id + " \t " + nombre);
            }
            
        } catch (Exception e) {
            System.out.println("Error en el sistema, intente más tarde!!" + e.getMessage());
        } finally {
            if(resultSet != null) { try{ resultSet.close();} catch(Exception e){}}
            if(statement != null) { try{ statement.close();} catch(Exception e){}}
            if(connection != null) { try{ connection.close();} catch(Exception e){}}
        }
    }
    
}
