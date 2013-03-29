package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.Servicio;
import java.util.List;

/**
 *
 * @author gian
 */
public interface ServicioDao {
    public Integer insertar(Servicio servicio);
    public void actualizar(Servicio servicio);
    public void eliminar(Servicio servicio);
    public List<Servicio> buscarTodos();
    public Servicio buscar(Integer id);
    public Servicio buscar(String correo);
    public List<Servicio> buscarPorNombre(String nombre);
}
