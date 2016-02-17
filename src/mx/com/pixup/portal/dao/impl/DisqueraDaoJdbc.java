/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.dao.impl;

import mx.com.pixup.portal.dao.interfaces.DisqueraDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.pixup.portal.db.DBConecta;
import mx.com.pixup.portal.model.Disquera;

/**
 *
 * @author JAVA-07
 */
public class DisqueraDaoJdbc implements DisqueraDao {

    public DisqueraDaoJdbc(){
    }
    
    @Override
    public Disquera insertDisquera(Disquera disquera) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String sql = "insert into disquera (nombre) values (?)";

        try {
            connection = DBConecta.getConnection();

            connection.setAutoCommit(false);
            
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, disquera.getNombre());

            preparedStatement.execute();

            connection.commit();
            resultSet = preparedStatement.getGeneratedKeys();
            
            resultSet.next();
            disquera.setId(resultSet.getInt(1));
            
            return disquera;
        } catch (SQLException ex) {
            Logger.getLogger(DisqueraDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }

        }
        return null;
    }

    @Override
    public List<Disquera> findAllDisqueras() {

        String sql = "select * from disquera";
        List<Disquera> disqueras = new ArrayList<Disquera>();
        
        try (Connection connection = DBConecta.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();) {
                
            while (resultSet.next()) {
                Disquera temp = new Disquera();
                temp.setId(resultSet.getInt("id"));
                temp.setNombre(resultSet.getString("nombre"));
                disqueras.add(temp);
            }

            
        } catch (SQLException e) {
            return null;
        }
        return disqueras;

    }

    @Override
    public Disquera findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Disquera updateDisquera(Disquera disquera){
        String sql = "update disquera set nombre = ? where id = ?";
        try (Connection connection = DBConecta.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {
            preparedStatement.setInt(2, disquera.getId());
            preparedStatement.setString(1, disquera.getNombre());
            
            preparedStatement.execute();
            
        } catch (SQLException e) {
            return null;
        }
        return disquera;

    }

    @Override
    public void deleteDisquera(Disquera disquera) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String sql = "delete from disquera where id = ?";

        try {
            connection = DBConecta.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, disquera.getId());

            preparedStatement.execute();

        } catch (SQLException e) {

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }

        }

    }

}

