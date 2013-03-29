package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.Local;
import java.util.List;

/**
 *
 * @author gian
 */
public interface LocalDao {
    public Integer insertar(Local local);
    public void actualizar(Local local);
    public void eliminar(Local local);
    public List<Local> buscarTodos();
    public Local buscar(Integer id);
    public Local buscar(String correo);
    public List<Local> buscarPorNombre(String nombre);
}
