package edu.upc.clase.demo.dao;

import edu.upc.clase.demo.entity.Instrumento;
import java.util.List;

/**
 *
 * @author gian
 */
public interface InstrumentoDao {
    public Integer insertar(Instrumento instrumento);
    public void actualizar(Instrumento instrumento);
    public void eliminar(Instrumento instrumento);
    public List<Instrumento> buscarTodos();
    public Instrumento buscar(Integer id);
    public Instrumento buscar(String correo);
    public List<Instrumento> buscarPorNombre(String nombre);
}
