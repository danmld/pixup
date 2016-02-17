/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author Daniel Morales
 */
public class DBConecta {

    
    /**
     * Objeto para guardar los parametros de conexion
     */
    private Properties dbProp = new Properties();

    /**
     * Statement para preparar las consultas
     */
    protected Statement stmt = null;
    
    /**
     * Singleton
     */

    private static DBConecta myDbConecta;
    
    /**
     * Lee los parámetros de conexion del archivo JDBC.properties y los guarda en en dbProp
     */
    public DBConecta() {
        ResourceBundle rsb = ResourceBundle.getBundle("setup");

        Enumeration enum1 = rsb.getKeys();
        Properties dbProp = new Properties();
        while (enum1.hasMoreElements()) {
            String sTmp = (String) enum1.nextElement();
            dbProp.setProperty(sTmp, rsb.getString(sTmp));
            //System.out.println("+" + sTmp );
        }
        this.dbProp = dbProp;
        System.out.println(dbProp);
    }
    
    public static DBConecta getInstance() 
    {
        if(myDbConecta == null)
        {
            myDbConecta = new DBConecta();
        }
        
        return myDbConecta;
    }
    
    public static BasicDataSource getDataSource()
    {
        DBConecta db = DBConecta.getInstance();
        
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(db.dbProp.getProperty("dataSource.className"));
        dataSource.setUsername(db.dbProp.getProperty("dataSource.username"));
        dataSource.setPassword(db.dbProp.getProperty("dataSource.password"));
        dataSource.setUrl(db.dbProp.getProperty("dataSource.url"));
        
        return dataSource;
    }
    
    public static Connection getConnection()
    {
        BasicDataSource datasource = DBConecta.getDataSource();
        Connection conn = null;
        try {
            conn = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }

    
}
