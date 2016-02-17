/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.db;

import java.sql.Connection;
import java.sql.DriverManager;
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
     * Objeto de Conexion a la base de datos
     */
    protected Connection oConn = null;

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
        ResourceBundle rsb = ResourceBundle.getBundle("JDBC");

        Enumeration enum1 = rsb.getKeys();
        Properties dbProp = new Properties();
        while (enum1.hasMoreElements()) {
            String sTmp = (String) enum1.nextElement();
            dbProp.setProperty(sTmp, rsb.getString(sTmp));
            System.out.println("+" + sTmp );
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
        dataSource.setDriverClassName(db.dbProp.getProperty("MySql.driver"));
        dataSource.setUsername(db.dbProp.getProperty("MySql.username"));
        dataSource.setPassword(db.dbProp.getProperty("MySql.password"));
        dataSource.setUrl(db.dbProp.getProperty("MySql.url"));
        
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

    /**
     * Se conecta a la base de datos mysql con los parametros del JDBC.properties
     */
    public void conecta() {
        try {
            //
            Class.forName(this.dbProp.getProperty("MySql.driver"));
            this.oConn = DriverManager.getConnection(
                    this.dbProp.getProperty("MySql.url"),
                    this.dbProp.getProperty("MySql.username"),
                    this.dbProp.getProperty("MySql.password"));
            this.stmt = oConn.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Se conecta a la base de datos access con los parametros del JDBC.properties
     */
    public void conectaAccess() {
        try {
            //
            Class.forName(this.dbProp.getProperty("access.driver"));
            this.oConn = DriverManager.getConnection(
                    this.dbProp.getProperty("access.url"),
                    this.dbProp.getProperty("access.username"),
                    this.dbProp.getProperty("access.password"));
            this.stmt = oConn.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Cierra la conexión para evitar dejar conexiones abiertas
     */
    public void desconecta(){
        try {
            if( !this.oConn.isClosed() ){
                oConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
