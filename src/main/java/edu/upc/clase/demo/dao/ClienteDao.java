package edu.upc.clase.demo.dao;
import edu.upc.clase.demo.entity.Cliente;
import java.util.List;

/**
 *
 * @author gian
 */
public interface ClienteDao {
    public Integer insertar(Cliente cliente);
    public void actualizar(Cliente cliente);
    public void eliminar(Cliente cliente);
    public List<Cliente> buscarTodos();
    public Cliente buscar(Integer id);
    public Cliente buscar(String correo);
    public List<Cliente> buscarPorNombre(String nombre);
}
