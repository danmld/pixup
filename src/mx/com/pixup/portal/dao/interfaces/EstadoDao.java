/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.dao.interfaces;

import java.util.List;
import mx.com.pixup.portal.model.Estado;

/**
 *
 * @author mflores
 */
public interface EstadoDao {
    Estado insertEstado(Estado estado);
    List<Estado> findAllEstados();
    Estado findById(Integer id);
    Estado updateEstado(Estado estado);
    void deleteEstado(Estado estado);
    
}
