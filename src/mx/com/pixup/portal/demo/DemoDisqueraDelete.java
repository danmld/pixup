/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author JAVA-13
 */
public class DemoDisqueraDelete {
    
    public static void main(String[] args) {
        System.out.println("BIENVENIDO A PIXUP");
        System.out.println("Mantenimiento catálogo disquera");
        System.out.println("Inserte el nombre de la disquera a eliminar: ");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        Connection connection = null;
        Statement statement = null;
        try {
            String nombreDisquera = br.readLine();
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("admin");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/pixup");
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            
            String sql = "delete from disquera where nombre = '" + 
                    nombreDisquera + "'";
            
            statement.execute(sql);
            
            System.out.println("Disquera: " + nombreDisquera + 
                    " eliminada con éxito");
            
        } catch (Exception e) {
            System.out.println("Error en el sistema, intente más tarde!!");
        } finally {
            if(statement != null) { try{ statement.close();} catch(Exception e){}}
            if(connection != null) { try{ connection.close();} catch(Exception e){}}
        }
    }
    
}
