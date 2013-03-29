package edu.upc.clases.demo;

import edu.upc.clase.demo.dao.ClienteDao;
import edu.upc.clase.demo.entity.Cliente;
//import edu.upc.clase.demo.service.ClienteService;
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
public class ClienteDaoTest {
    
    private static Logger log = LoggerFactory.getLogger(ClienteDaoTest.class);
    
    @Autowired
    ClienteDao clienteDao;

    @Autowired 
//    ClienteService clienteService;
        
    @Test
    public void testInsertarCliente() {
        Cliente cliente = new Cliente("Carlos","Caballero","Galvan","ccaballero@alquileres.com","2013/03/21","prueba","ccaballero","123","123");
        Integer id = clienteDao.insertar(cliente);
        Assert.assertNotNull(id);
    }

    /**
     * Quiero probar que despues de haber registrado un valor, lo pueda actualizar
     * Primero registro, luego grabo, luego busco, actualizo.
     */
    @Test
    public void testActualizarCliente() {
        Cliente cliente = new Cliente("Carlos","Caballero","Galvan","ccaballero@alquileres.com","2013/03/21","prueba","ccaballero","123","123");
        Integer id = clienteDao.insertar(cliente);
        cliente = clienteDao.buscar(id);
        cliente.setNombre("Javier Corzo");
        clienteDao.actualizar(cliente);
        cliente = clienteDao.buscar(id);
        Assert.assertEquals("Javier Corzo", cliente.getNombre());
    }

    @Test
    public void testEliminarCliente() {
        Cliente cliente = new Cliente("Carlos","Caballero","Galvan","ccaballero@alquileres.com","2013/03/21","prueba","ccaballero","123","123");
        Integer id = clienteDao.insertar(cliente);
        cliente = clienteDao.buscar(id);
        clienteDao.eliminar(cliente);
        cliente = clienteDao.buscar(id);
        Assert.assertNull(cliente);
    }

    @Test
    public void testBuscarPorLetras() {
        Cliente cliente = new Cliente("Carlos","Caballero","Galvan","ccaballero@alquileres.com","2013/03/21","prueba","ccaballero","123","123");
        Integer id = clienteDao.insertar(cliente);
        cliente = clienteDao.buscar(-8);
        Assert.assertNull(cliente);
    }
}
