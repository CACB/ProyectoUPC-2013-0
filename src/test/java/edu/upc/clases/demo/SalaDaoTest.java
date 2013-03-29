package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.SalaDao;
import edu.upc.clase.demo.entity.Sala;
import edu.upc.clase.demo.dao.LocalDao;
import edu.upc.clase.demo.entity.Local;
//import edu.upc.clase.demo.service.SalaService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-base-test.xml")
@Transactional
public class SalaDaoTest {
    
    private static Logger log = LoggerFactory.getLogger(SalaDaoTest.class);
    
    @Autowired
    SalaDao salaDao;
    
    @Autowired
    LocalDao localDao;

    @Autowired 
//    SalaService salaService;
        
    @Test
    public void testInsertarsala() {
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local); 
        
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer id = salaDao.insertar(sala);
        Assert.assertNotNull(id);
    }

    /**
     * Quiero probar que despues de haber registrado un valor, lo pueda actualizar
     * Primero registro, luego grabo, luego busco, actualizo.
     */
    @Test
    public void testActualizarSala() {
         Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local); 
        
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer id = salaDao.insertar(sala);
        sala = salaDao.buscar(id);
        sala.setNombre("Vip");
        salaDao.actualizar(sala);
        sala = salaDao.buscar(id);
        Assert.assertEquals("Vip", sala.getNombre());
    }

    @Test
    public void testEliminarSala() {
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",1);
        Integer id = salaDao.insertar(sala);
        sala = salaDao.buscar(id);
        salaDao.eliminar(sala);
        sala = salaDao.buscar(id);
        Assert.assertNull(sala);
    }

    @Test
    public void testBuscarPorLetras() {
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",1);
        Integer id = salaDao.insertar(sala);
        sala = salaDao.buscar(-8);
        Assert.assertNull(sala);
    }
}
