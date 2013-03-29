package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.Sala;
import java.util.List;

/**
 *
 * @author gian
 */
public interface SalaDao {
    public Integer insertar(Sala sala);
    public void actualizar(Sala sala);
    public void eliminar(Sala sala);
    public List<Sala> buscarTodos();
    public Sala buscar(Integer id);
    public Sala buscar(String correo);
    public List<Sala> buscarPorNombre(String nombre);
}
