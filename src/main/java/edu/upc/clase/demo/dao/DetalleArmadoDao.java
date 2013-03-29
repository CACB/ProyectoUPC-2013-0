package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.DetalleArmado;
import java.util.List;

/**
 *
 * @author gian
 */
public interface DetalleArmadoDao {
    public Integer insertar(DetalleArmado detallearmado);
//    public void actualizar(DetalleArmado detallearmado);
    public void eliminar(DetalleArmado detallearmado); public List<DetalleArmado> buscarTodos();
    public DetalleArmado buscar(Integer id);
//    public DetalleArmado buscar(String correo);
//    public List<DetalleArmado> buscarPorNombre(String nombre);
}
