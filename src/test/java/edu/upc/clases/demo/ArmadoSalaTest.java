package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.ArmadoSalaDao;
import edu.upc.clase.demo.entity.ArmadoSala;
import edu.upc.clase.demo.dao.SalaDao;
import edu.upc.clase.demo.entity.Sala;;
import edu.upc.clase.demo.dao.LocalDao;
import edu.upc.clase.demo.entity.Local;

//import edu.upc.clase.demo.service.ArmadoSalaService;
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
public class ArmadoSalaTest {
    
    private static Logger log = LoggerFactory.getLogger(ArmadoSalaTest.class);
    
    @Autowired
    ArmadoSalaDao armadosalaDao;
    
    @Autowired
    SalaDao salaDao;
    
    @Autowired
    LocalDao localDao;

    @Autowired 
//    ArmadoSalaService armadosalaService;
        
    @Test
    public void testInsertarArmadoSala() {
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);
        
        ArmadoSala armadosala = new ArmadoSala(idsala,12);
        Integer id = armadosalaDao.insertar(armadosala);
        Assert.assertNotNull(id);
    }

    /**
     * Quiero probar que despues de haber registrado un valor, lo pueda actualizar
     * Primero registro, luego grabo, luego busco, actualizo.
     */
    @Test
    public void testActualizarArmadoSala() {
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);
        
        ArmadoSala armadosala = new ArmadoSala(idsala,12);
        Integer id = armadosalaDao.insertar(armadosala);
        armadosala = armadosalaDao.buscar(id);
        armadosala.setCosto(16);
        armadosalaDao.actualizar(armadosala);
        armadosala = armadosalaDao.buscar(id);
        Assert.assertEquals(16.0, armadosala.getCosto(),0.1);
    }

    @Test
    public void testEliminarArmadoSala() {
        Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);
        
        ArmadoSala armadosala = new ArmadoSala(idsala,12);
        Integer id = armadosalaDao.insertar(armadosala);
        armadosala = armadosalaDao.buscar(id);
        armadosalaDao.eliminar(armadosala);
        armadosala = armadosalaDao.buscar(id);
        Assert.assertNull(armadosala);
    }

    @Test
    public void testBuscarPorLetras() {
         Local local = new Local ("Administrador");
        Integer idlocal = localDao.insertar(local);         
        Sala sala = new Sala("Premium","Miraflores",50,"Moderna",idlocal);
        Integer idsala = salaDao.insertar(sala);
        
        ArmadoSala armadosala = new ArmadoSala(idsala,12);
        Integer id = armadosalaDao.insertar(armadosala);
        armadosala = armadosalaDao.buscar(-8);
        Assert.assertNull(armadosala);
    }
}
