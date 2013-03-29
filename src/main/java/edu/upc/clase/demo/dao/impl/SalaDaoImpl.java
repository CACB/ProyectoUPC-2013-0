package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.SalaDao;
import edu.upc.clase.demo.entity.Sala;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gian
 */
@Repository
public class SalaDaoImpl extends SimpleJdbcDaoSupport implements SalaDao {

    private static Logger log = LoggerFactory.getLogger(SalaDaoImpl.class);

    @Autowired
    public SalaDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(Sala sala) {

        getJdbcTemplate().update(
                "insert into sala (nombre,direccion,capacidad, caracteristicas, idlocal) values (?, ?, ?, ?, ?)",
                sala.getNombre(), sala.getDireccion(), sala.getCapacidad(), sala.getCaracteristicas(),sala.getIdlocal());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }

    @Override
    public void actualizar(Sala sala) {
        getJdbcTemplate().update(
                "update sala set nombre = ?, direccion = ?, capacidad = ?, caracteristicas = ?, idlocal = ? where idsala = ?",
                sala.getNombre(), sala.getDireccion(), sala.getCapacidad(), sala.getCaracteristicas(),sala.getIdlocal(),sala.getIdsala());
    }

    @Override
    public void eliminar(Sala sala) {
        getJdbcTemplate().update(
                "delete from sala where idsala = ?", sala.getIdsala());
    }

    @Override
    public Sala buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idsala,nombre,direccion,capacidad, caracteristicas, idlocal from sala where idsala=?",
                    new BeanPropertyRowMapper<Sala>(Sala.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Sala> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select nombre,direccion,capacidad, caracteristicas, idlocal from sala",
                new BeanPropertyRowMapper<Sala>(Sala.class));
    }

    @Override
    public Sala buscar(String direccion) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select nombre,direccion,capacidad, caracteristicas, idlocal from sala where direccion=?",
                    new BeanPropertyRowMapper<Sala>(Sala.class), direccion);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Sala> buscarPorNombre(String nombre) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("nombre","%"+nombre+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from sala where nombre like :nombre",
                    new BeanPropertyRowMapper<Sala>(Sala.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}