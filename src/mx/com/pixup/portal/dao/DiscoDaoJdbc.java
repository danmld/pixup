/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.pixup.portal.db.DBConecta;
import mx.com.pixup.portal.model.Disco;
import mx.com.pixup.portal.model.Disco;
import mx.com.pixup.portal.model.Disquera;

/**
 *
 * @author daniel
 */
public class DiscoDaoJdbc implements DiscoDao {

    @Override
    public Disco insertDisco(Disco disco) {
        Connection connection = DBConecta.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String sql = "insert into disco "
                + "(titulo, "
                + "fecha_lanzamiento, "
                + "precio,"
                + "cantidad_disponible," 
                + "id_idioma, " 
                + "id_pais, " 
                + "id_disquera, " 
                + "id_genero_musical, " 
                + "id_promocion, " 
                + "id_iva) "
                + "values (?,STR_TO_DATE(?,'%Y-%d-%m'),?,?,?,?,?,?,?,?) ";
        
        try {            
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setString(2, disco.getFecha_lanzamiento());
            preparedStatement.setFloat(3, disco.getPrecio());
            preparedStatement.setInt(4, disco.getCantidad_disponible());
            preparedStatement.setInt(5, disco.getId_idioma());
            preparedStatement.setInt(6, disco.getId_pais());            
            preparedStatement.setInt(7, disco.getId_disquera());
            preparedStatement.setInt(8, disco.getId_genero_musical());
            preparedStatement.setInt(9, disco.getId_promocion());
            preparedStatement.setInt(10, disco.getId_iva());            
            preparedStatement.execute();   
            
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            disco.setId(resultSet.getInt(1));
            
        } catch (Exception e) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        finally {
            if(preparedStatement != null) { try{ preparedStatement.close();} catch(Exception e){}}
            if(connection != null) { try{ connection.close();} catch(Exception e){}}
        }
        
        return disco;
    }

    @Override
    public Disco updateDisco(Disco disco) {
        String sql = "update disco "
                + " SET titulo = ?, "
                + " fecha_lanzamiento = STR_TO_DATE(?,'%Y-%m-%d'), "                        
                + " precio = ?, "
                + " cantidad_disponible = ?, "
                + " id_idioma = ?, "
                + " id_pais = ?, "
                + " id_disquera = ?, "
                + " id_genero_musical = ?,"
                + " id_promocion = ?, "
                + " id_iva = ?"
                + " where id = ?";
        Connection connection = DBConecta.getConnection();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, disco.getTitulo());
            preparedStatement.setString(2, disco.getFecha_lanzamiento());
            preparedStatement.setFloat(3, disco.getPrecio());
            preparedStatement.setInt(4, disco.getCantidad_disponible());
            preparedStatement.setInt(5, disco.getId_idioma());
            preparedStatement.setInt(6, disco.getId_pais());            
            preparedStatement.setInt(7, disco.getId_disquera());
            preparedStatement.setInt(8, disco.getId_genero_musical());
            preparedStatement.setInt(9, disco.getId_promocion());
            preparedStatement.setInt(10, disco.getId_iva());
            preparedStatement.setInt(11, disco.getId());
            
            System.out.println("q" + preparedStatement.toString());
            preparedStatement.execute();
            return disco;
        } catch (Exception e) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, e);
        } 
        return disco;
    }

    @Override
    public void deleteDisco(Disco disco) {
        Connection connection = DBConecta.getConnection();
        String sql = "delete from disco where id = ?";
        
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {            
            preparedStatement.setInt(1, disco.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<Disco> findAllDiscos() {
        String sql = "select * from disco";
        List<Disco> discos = new ArrayList<Disco>();
        Connection connection = DBConecta.getConnection();
        
        try (             
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while(resultSet.next()) {                
                Disco disco = new Disco();
                disco.setId(resultSet.getInt("id"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setFecha_lanzamiento(resultSet.getString("fecha_lanzamiento"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setCantidad_disponible(resultSet.getInt("cantidad_disponible"));
                disco.setId_idioma(resultSet.getInt("id_idioma"));
                disco.setId_pais(resultSet.getInt("id_pais"));
                disco.setId_disquera(resultSet.getInt("id_disquera"));
                disco.setId_genero_musical(resultSet.getInt("id_genero_musical"));
                disco.setId_promocion(resultSet.getInt("id_promocion"));
                disco.setId_iva(resultSet.getInt("id_iva"));
                discos.add(disco);
               
            }
        } catch (Exception e) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, e);
        } 
        return discos;
    }

    @Override
    public Disco findById(Long idDisco) {
        String sql = "select *, DATE_FORMAT(fecha_lanzamiento,'%Y-%m-%d') as fecha_lanzamiento from disco "
                + " where id = " + idDisco;
        Disco disco = new Disco();
        Connection connection = DBConecta.getConnection();
        
        try (
             
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while(resultSet.next()) {                                
                disco.setId(resultSet.getInt("id"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setFecha_lanzamiento(resultSet.getString("fecha_lanzamiento"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setCantidad_disponible(resultSet.getInt("cantidad_disponible"));
                disco.setId_idioma(resultSet.getInt("id_idioma"));
                disco.setId_pais(resultSet.getInt("id_pais"));
                disco.setId_disquera(resultSet.getInt("id_disquera"));
                disco.setId_genero_musical(resultSet.getInt("id_genero_musical"));
                disco.setId_promocion(resultSet.getInt("id_promocion"));
                disco.setId_iva(resultSet.getInt("id_iva"));
               
            }
        } catch (Exception e) {
            Logger.getLogger(DBConecta.class.getName()).log(Level.SEVERE, null, e);
        } 
        return disco;
    }
    
    public static void main(String[] args) {
        DiscoDaoJdbc discoDao =  new DiscoDaoJdbc();
        List<Disco> listDiscos = discoDao.findAllDiscos();
        for(Disco disco : listDiscos)
        {
            System.out.println("id: " + disco.getFecha_lanzamiento() );
        }
        
        Disco d = discoDao.findById((long)1);
        System.out.println("disco " + d.getTitulo() + d.getFecha_lanzamiento());
        
        d.setFecha_lanzamiento("1983-10-21");
        
        discoDao.updateDisco(d);
        System.out.println("disco " + d.getTitulo() + d.getFecha_lanzamiento());
        
    }
    
}
