package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.DetalleArmadoDao;
import edu.upc.clase.demo.entity.DetalleArmado;
import edu.upc.clase.demo.dao.InstrumentoDao;
import edu.upc.clase.demo.entity.Instrumento;
import edu.upc.clase.demo.dao.SalaDao;
import edu.upc.clase.demo.entity.Sala;
import edu.upc.clase.demo.dao.ArmadoSalaDao;
import edu.upc.clase.demo.entity.ArmadoSala;
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
public class DetalleArmadoDaoTest {
    
    private static Logger log = LoggerFactory.getLogger(DetalleArmadoDaoTest.class);
    
    @Autowired
    DetalleArmadoDao detallearmadoDao;
    
    @Autowired
    InstrumentoDao instrumentoDao;
   
    @Autowired
    SalaDao salaDao;
     
    @Autowired
    ArmadoSalaDao armadosalaDao;
    
    @Autowired
    LocalDao localDao;

    @Autowired 
//    DetalleArmadoService detallearmadoService;
        
    @Test
    public void testInsertarDetalleArmado() {
        Instrumento instrumento = new Instrumento("viento","selmer","cc2013","2013","Saxo Frances",25);
        Integer idinstrumento = instrumentoDao.insertar(instrumento);
        
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);
        ArmadoSala armadosala = new ArmadoSala(idsala,12);
        Integer idarmadosala = armadosalaDao.insertar(armadosala);
        
        DetalleArmado detallearmado = new DetalleArmado(idinstrumento,idarmadosala);
        Integer id = detallearmadoDao.insertar(detallearmado);
        Assert.assertNotNull(id);
    }

  

    @Test
    public void testEliminarDetalleArmado() {
        Instrumento instrumento = new Instrumento("viento","selmer","cc2013","2013","Saxo Frances",25);
        Integer idinstrumento = instrumentoDao.insertar(instrumento);
        
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);
        ArmadoSala armadosala = new ArmadoSala(idsala,12);
        Integer idarmadosala = armadosalaDao.insertar(armadosala);
        
        DetalleArmado detallearmado = new DetalleArmado(idinstrumento,idarmadosala);
        Integer id = detallearmadoDao.insertar(detallearmado);
        
        detallearmado = detallearmadoDao.buscar(id);
        detallearmadoDao.eliminar(detallearmado);
        detallearmado = detallearmadoDao.buscar(id);
        Assert.assertNull(detallearmado);
    }

    @Test
    public void testBuscarPorLetras() {
        Instrumento instrumento = new Instrumento("viento","selmer","cc2013","2013","Saxo Frances",25);
        Integer idinstrumento = instrumentoDao.insertar(instrumento);
        
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);
        ArmadoSala armadosala = new ArmadoSala(idsala,12);
        Integer idarmadosala = armadosalaDao.insertar(armadosala);
        
        DetalleArmado detallearmado = new DetalleArmado(idinstrumento,idarmadosala);
        Integer id = detallearmadoDao.insertar(detallearmado);
        detallearmado = detallearmadoDao.buscar(-8);
        Assert.assertNull(detallearmado);
    }
}
