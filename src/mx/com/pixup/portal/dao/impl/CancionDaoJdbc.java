package mx.com.pixup.portal.dao.impl;

import mx.com.pixup.portal.dao.interfaces.CancionDao;
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
import mx.com.pixup.portal.model.Cancion;

/**
 *
 * @author rodvazqu
 */
public class CancionDaoJdbc implements CancionDao {

    public CancionDaoJdbc() {

    }

    @Override
    public Cancion insertCancion(Cancion cancion) {
        ResultSet resultSet = null;
        String sql = "insert into cancion(nombre,duracion) values (?,?)";
        try (Connection connection = DBConecta.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cancion.getNombre());
            preparedStatement.setString(2, cancion.getDuracion());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            cancion.setId(resultSet.getInt(1));
            return cancion;
        } catch (Exception e) {
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public List<Cancion> findAllCancions() {

        String sql = "select * from cancion order by id";
        List<Cancion> canciones = new ArrayList<>();
        try (Connection connection = DBConecta.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                Cancion cancion = new Cancion();
                cancion.setId(resultSet.getInt(1));
                cancion.setNombre(resultSet.getString(2));
                cancion.setDuracion(resultSet.getString(3));
                canciones.add(cancion);
            }
        } catch (Exception e) {
            return null;
        }
        return canciones;

    }

    @Override
    public Cancion findById(Integer id) {
        String sql = "SELECT * FROM cancion WHERE id = ?";
        Cancion cancion = new Cancion();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = DBConecta.getConnection();) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cancion.setId(resultSet.getInt("id"));
            cancion.setNombre(resultSet.getString("nombre"));
            cancion.setDuracion(resultSet.getString("duracion"));

        } catch (Exception e) {
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
        }
        return cancion;
    }

    @Override
    public Cancion updateCancion(Cancion cancion) {
        String sql = "update cancion set nombre = ?,duracion = ? where id = ?";
        try (Connection connection = DBConecta.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, cancion.getNombre());
            if (cancion.getDuracion() != null && cancion.getDuracion().length() > 0) {
                preparedStatement.setString(2, cancion.getDuracion());
            } else {
                preparedStatement.setNull(2, java.sql.Types.NULL);
            }

            preparedStatement.setInt(3, cancion.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            return null;
        }
        return cancion;

    }

    @Override
    public void deleteCancion(Cancion cancion) {

        String sql = "delete from cancion where id = ?";

        try (Connection connection = DBConecta.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, cancion.getId());
            preparedStatement.execute();
        } catch (Exception e) {
        }
    }

}
