package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.ArmadoSala;
import java.util.List;

/**
 *
 * @author gian
 */
public interface ArmadoSalaDao {
    public Integer insertar(ArmadoSala armadosala);
    public void actualizar(ArmadoSala armadosala);
    public void eliminar(ArmadoSala armadosala);
    public List<ArmadoSala> buscarTodos();
    public ArmadoSala buscar(Integer id);
    public ArmadoSala buscar(String correo);
    public List<ArmadoSala> buscarPorNombre(String nombre);
}
