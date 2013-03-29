package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.DetalleReservaDao;
import edu.upc.clase.demo.entity.DetalleReserva;
import edu.upc.clase.demo.dao.InstrumentoDao;
import edu.upc.clase.demo.entity.Instrumento;
import edu.upc.clase.demo.dao.ServicioDao;
import edu.upc.clase.demo.entity.Servicio;
import edu.upc.clase.demo.dao.SalaDao;
import edu.upc.clase.demo.entity.Sala;
import edu.upc.clase.demo.dao.ReservaDao;
import edu.upc.clase.demo.entity.Reserva;
import edu.upc.clase.demo.dao.LocalDao;
import edu.upc.clase.demo.entity.Local;

import edu.upc.clase.demo.entity.*;
//import edu.upc.clase.demo.service.DetalleArmadoService;
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
public class DetalleReservaDaoTest {
    
    private static Logger log = LoggerFactory.getLogger(DetalleReservaDaoTest.class);
    
    @Autowired
    DetalleReservaDao detallereservaDao;
    
    @Autowired
    InstrumentoDao instrumentoDao;
   
    @Autowired
    SalaDao salaDao;
     
    @Autowired
    ReservaDao reservaDao;
    
    @Autowired
    LocalDao localDao;
    
    @Autowired
    ServicioDao servicioDao;

    @Autowired 
//    DetalleArmadoService detallearmadoService;
        
    @Test
    public void testInsertarDetalleReserva() {
        Instrumento instrumento = new Instrumento("viento","selmer","cc2013","2013","Saxo Frances",25);
        Integer idinstrumento = instrumentoDao.insertar(instrumento);
        
        Servicio servicio = new Servicio("ALquiler",20);
        Integer idservicio = servicioDao.insertar(servicio);        
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);        
        Reserva reserva = new Reserva("2013/03/21",50,15,idservicio,idsala);
        Integer idreserva = reservaDao.insertar(reserva);
        
        DetalleReserva detallereserva = new DetalleReserva(idinstrumento,idreserva);
        Integer id = detallereservaDao.insertar(detallereserva);
        Assert.assertNotNull(id);
    }

  

    @Test
    public void testEliminarDetalleReserva() {
        Instrumento instrumento = new Instrumento("viento","selmer","cc2013","2013","Saxo Frances",25);
        Integer idinstrumento = instrumentoDao.insertar(instrumento);
        
        Servicio servicio = new Servicio("ALquiler",20);
        Integer idservicio = servicioDao.insertar(servicio);        
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);        
        Reserva reserva = new Reserva("2013/03/21",50,15,idservicio,idsala);
        Integer idreserva = reservaDao.insertar(reserva);
        
        DetalleReserva detallereserva = new DetalleReserva(idinstrumento,idreserva);
        Integer id = detallereservaDao.insertar(detallereserva);
        
        detallereserva = detallereservaDao.buscar(id);
        detallereservaDao.eliminar(detallereserva);
        detallereserva = detallereservaDao.buscar(id);
        Assert.assertNull(detallereserva);
    }

    @Test
    public void testBuscarPorLetras() {
        Instrumento instrumento = new Instrumento("viento","selmer","cc2013","2013","Saxo Frances",25);
        Integer idinstrumento = instrumentoDao.insertar(instrumento);
        
        Servicio servicio = new Servicio("ALquiler",20);
        Integer idservicio = servicioDao.insertar(servicio);        
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);        
        Reserva reserva = new Reserva("2013/03/21",50,15,idservicio,idsala);
        Integer idreserva = reservaDao.insertar(reserva);
        
        DetalleReserva detallereserva = new DetalleReserva(idinstrumento,idreserva);
        Integer id = detallereservaDao.insertar(detallereserva);
        
        detallereserva = detallereservaDao.buscar(-8);
        Assert.assertNull(detallereserva);
    }
}
