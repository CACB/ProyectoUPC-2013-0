package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.InstrumentoDao;
import edu.upc.clase.demo.entity.Instrumento;
//import edu.upc.clase.demo.service.UsuarioService;
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
public class InstrumentoDaoTest {
    
    private static Logger log = LoggerFactory.getLogger(InstrumentoDaoTest.class);
    
    @Autowired
    InstrumentoDao instrumentoDao;

    @Autowired 
//    UsuarioService usuarioService;
        
    @Test
    public void testInsertarInstrumento() {
        Instrumento instrumento = new Instrumento("viento","selmer","cc2013","2013","Saxo Frances",25);
        Integer id = instrumentoDao.insertar(instrumento);
        Assert.assertNotNull(id);
    }

    /**
     * Quiero probar que despues de haber registrado un valor, lo pueda actualizar
     * Primero registro, luego grabo, luego busco, actualizo.
     */
    @Test
    public void testActualizarInstrumento() {
        Instrumento instrumento = new Instrumento("viento","Selmer","cc2013","2013","Saxo Frances",25);
        Integer id = instrumentoDao.insertar(instrumento);
        instrumento = instrumentoDao.buscar(id);
        instrumento.setMarca("Selmer");
        instrumentoDao.actualizar(instrumento);
        instrumento = instrumentoDao.buscar(id);
        Assert.assertEquals("Selmer", instrumento.getMarca());
    }

    @Test
    public void testEliminarInstrumento() {
        Instrumento instrumento = new Instrumento("viento","Selmer","cc2013","2013","Saxo Frances",25);
        Integer id = instrumentoDao.insertar(instrumento);
        instrumento = instrumentoDao.buscar(id);
        instrumentoDao.eliminar(instrumento);
        instrumento = instrumentoDao.buscar(id);
        Assert.assertNull(instrumento);
    }

    @Test
    public void testBuscarPorLetras() {
        Instrumento instrumento = new Instrumento("viento","Selmer","cc2013","2013","Saxo Frances",25);
        Integer id = instrumentoDao.insertar(instrumento);
        instrumento = instrumentoDao.buscar(-8);
        Assert.assertNull(instrumento);
    }
}
