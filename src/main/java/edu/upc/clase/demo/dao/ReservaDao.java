package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.Reserva;
import java.util.List;

/**
 *
 * @author gian
 */
public interface ReservaDao {
    public Integer insertar(Reserva reserva);
    public void actualizar(Reserva reserva);
    public void eliminar(Reserva reserva);
    public List<Reserva> buscarTodos();
    public Reserva buscar(Integer id);
    public Reserva buscar(String correo);
    public List<Reserva> buscarPorNombre(String nombre);
}
