package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.LocalDao;
import edu.upc.clase.demo.entity.Local;
//import edu.upc.clase.demo.service.LocalService;
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
public class LocalDaoTest {
    
    private static Logger log = LoggerFactory.getLogger(LocalDaoTest.class);
    
    @Autowired
    LocalDao localDao;

    @Autowired 
//    LocalService localService;
        
    @Test
    public void testInsertarLocal() {
        Local local = new Local("Miraflores");
        Integer id = localDao.insertar(local);
        Assert.assertNotNull(id);
    }

    /**
     * Quiero probar que despues de haber registrado un valor, lo pueda actualizar
     * Primero registro, luego grabo, luego busco, actualizo.
     */
    @Test
    public void testActualizarLocal() {
        Local local = new Local("Mirafloes");
        Integer id = localDao.insertar(local);
        local = localDao.buscar(id);
        local.setDescripcion("San Isidro");
        localDao.actualizar(local);
        local = localDao.buscar(id);
        Assert.assertEquals("San Isidro", local.getDescripcion());
    }

    @Test
    public void testEliminarLocal() {
        Local local = new Local("Miraflores");
        Integer id = localDao.insertar(local);
        local = localDao.buscar(id);
        localDao.eliminar(local);
        local = localDao.buscar(id);
        Assert.assertNull(local);
    }

    @Test
    public void testBuscarPorLetras() {
        Local local = new Local("Miraflores");
        Integer id = localDao.insertar(local);
        local = localDao.buscar(-8);
        Assert.assertNull(local);
    }
}
