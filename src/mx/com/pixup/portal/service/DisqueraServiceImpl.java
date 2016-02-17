/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.service;

import java.util.List;
import mx.com.pixup.portal.dao.DisqueraDao;
import mx.com.pixup.portal.dao.DisqueraDaoJdbc;
import mx.com.pixup.portal.model.Disquera;

/**
 *
 * @author JAVA-13
 */
public class DisqueraServiceImpl implements DisqueraService {
    
    private DisqueraDao disqueraDao;
    
    public DisqueraServiceImpl() {
        this.disqueraDao = new DisqueraDaoJdbc();
    }

    @Override
    public Disquera guardarDisquera(Disquera disquera) {
        return disqueraDao.insertDisquera(disquera);
    }

    @Override
    public void borrarDisquera(Disquera disquera) {
        disqueraDao.deleteDisquera(disquera);
    }

    @Override
    public Disquera actualizarDisquera(Disquera disquera) {
        return disqueraDao.updateDisquera(disquera);
    }

    @Override
    public List<Disquera> consultarDisquera(Disquera disquera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Disquera obtenerDisquera(Integer idDisquera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Disquera obtenerDisquera(String nombreDisquera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Disquera> obtenerDisqueras() {
        return disqueraDao.findAllDisqueras();
    }
    
}
