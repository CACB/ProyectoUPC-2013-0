package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.ServicioDao;
import edu.upc.clase.demo.entity.Servicio;
//import edu.upc.clase.demo.service.ServicioService;
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
public class ServicioDaoTest {
    
    private static Logger log = LoggerFactory.getLogger(ServicioDaoTest.class);
    
    @Autowired
    ServicioDao servicioDao;

    @Autowired 
//    UServicioService servicioService;
        
    @Test
    public void testInsertarServicio() {
        Servicio servicio = new Servicio("ALquiler",20);
        Integer id = servicioDao.insertar(servicio);
        Assert.assertNotNull(id);
    }

    /**
     * Quiero probar que despues de haber registrado un valor, lo pueda actualizar
     * Primero registro, luego grabo, luego busco, actualizo.
     */
    @Test
    public void testActualizarServicio() {
        Servicio servicio = new Servicio("ALquiler",20);
        Integer id = servicioDao.insertar(servicio);
        servicio = servicioDao.buscar(id);
        servicio.setDescripcion("Javier Corzo");
        servicioDao.actualizar(servicio);
        servicio = servicioDao.buscar(id);
        Assert.assertEquals("Javier Corzo", servicio.getDescripcion());
    }

    @Test
    public void testEliminarServicio() {
        Servicio servicio = new Servicio("ALquiler",20);
        Integer id = servicioDao.insertar(servicio);
        servicio = servicioDao.buscar(id);
        servicioDao.eliminar(servicio);
        servicio = servicioDao.buscar(id);
        Assert.assertNull(servicio);
    }

    @Test
    public void testBuscarPorLetras() {
        Servicio servicio = new Servicio("ALquiler",20);
        Integer id = servicioDao.insertar(servicio);
        servicio = servicioDao.buscar(-8);
        Assert.assertNull(servicio);
    }
}
