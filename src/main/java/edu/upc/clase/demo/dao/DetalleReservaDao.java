package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.DetalleReserva;
import java.util.List;

/**
 *
 * @author gian
 */
public interface DetalleReservaDao {
    public Integer insertar(DetalleReserva detallereserva);
    public void eliminar(DetalleReserva detallearmado); 
    public List<DetalleReserva> buscarTodos();
    public DetalleReserva buscar(Integer id);
}
