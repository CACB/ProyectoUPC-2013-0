package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.ServicioDao;
import edu.upc.clase.demo.entity.Servicio;
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
public class ServicioDaoImpl extends SimpleJdbcDaoSupport implements ServicioDao {

    private static Logger log = LoggerFactory.getLogger(ServicioDaoImpl.class);

    @Autowired
    public ServicioDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(Servicio servicio) {

        getJdbcTemplate().update(
                "insert into servicio (descripcion,costo) values (?, ?)",
                servicio.getDescripcion(), servicio.getCosto());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }

    @Override
    public void actualizar(Servicio servicio) {
        getJdbcTemplate().update(
                "update servicio set descripcion = ?, costo = ? where idservicio = ?",
                servicio.getDescripcion(), servicio.getCosto(), servicio.getIdservicio());
    }

    @Override
    public void eliminar(Servicio servicio) {
        getJdbcTemplate().update(
                "delete from servicio where idservicio = ?", servicio.getIdservicio());
    }

    @Override
    public Servicio buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idservicio, descripcion, costo from servicio where idservicio=?",
                    new BeanPropertyRowMapper<Servicio>(Servicio.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Servicio> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select idservicio, descripcion, costo from servicio",
                new BeanPropertyRowMapper<Servicio>(Servicio.class));
    }

    @Override
    public Servicio buscar(String descripcion) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idservicio, descripcion, costo from servicio where descripcion=?",
                    new BeanPropertyRowMapper<Servicio>(Servicio.class), descripcion);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Servicio> buscarPorNombre(String nombre) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("nombre","%"+nombre+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from servicio where nombre like :nombre",
                    new BeanPropertyRowMapper<Servicio>(Servicio.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}