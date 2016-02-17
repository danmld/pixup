/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.service;

import java.util.List;
import mx.com.pixup.portal.model.Disquera;

/**
 *
 * @author JAVA-13
 */
public interface DisqueraService {
    
    Disquera guardarDisquera(Disquera disquera);
    void borrarDisquera(Disquera disquera);
    Disquera actualizarDisquera(Disquera disquera);
    List<Disquera> consultarDisquera(Disquera disquera);
    Disquera obtenerDisquera(Integer idDisquera);
    Disquera obtenerDisquera(String nombreDisquera);
    List<Disquera> obtenerDisqueras();
    
}
