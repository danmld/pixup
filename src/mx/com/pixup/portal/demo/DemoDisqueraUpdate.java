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
public class DemoDisqueraUpdate {
    
    public static void main(String[] args) {
        System.out.println("BIENVENIDO A PIXUP");
        System.out.println("Mantenimiento catálogo disquera");
        System.out.println("Actualización de Disquera");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
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
            
            String sql = "select id, nombre from disquera order by nombre";
            
            resultSet = statement.executeQuery(sql);
            
            System.out.println("Id Disquera: \t Nombre Disquera");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " \t " + resultSet.getString("nombre"));
            }
            
            System.out.println("Proporcione el id de la disquera a actualizar: ");
            String idDisquera = br.readLine();
            
            System.out.println("Proporcione el nuevo nombre de la disquera: ");
            String nombreDisquera = br.readLine();
            
            sql = "update disquera set nombre = '" + nombreDisquera + "' where id = " + idDisquera;
            
            statement.execute(sql);
            
            System.out.println("Disqueras Actualizadas:");
            
            sql = "select id, nombre from disquera order by nombre desc";
            
            resultSet = statement.executeQuery(sql);
            
            System.out.println("Id Disquera: \t Nombre Disquera");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " \t " + resultSet.getString("nombre"));
            }
            
        } catch (Exception e) {
            System.out.println("Error en el sistema, intente más tarde!!");
        } finally {
            if(resultSet != null) { try{ resultSet.close();} catch(Exception e){}}
            if(statement != null) { try{ statement.close();} catch(Exception e){}}
            if(connection != null) { try{ connection.close();} catch(Exception e){}}
        }
    }
    
}
