package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.LocalDao;
import edu.upc.clase.demo.entity.Local;
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
public class LocalDaoImpl extends SimpleJdbcDaoSupport implements LocalDao {

    private static Logger log = LoggerFactory.getLogger(LocalDaoImpl.class);

    @Autowired
    public LocalDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(Local local) {

        getJdbcTemplate().update(
                "insert into local (descripcion) values (?)",
                local.getDescripcion());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }

    @Override
    public void actualizar(Local local) {
        getJdbcTemplate().update(
                "update local set descripcion = ? where idlocal = ?",
                local.getDescripcion(), local.getIdlocal());
    }

    @Override
    public void eliminar(Local local) {
        getJdbcTemplate().update(
                "delete from local where idlocal = ?", local.getIdlocal());
    }

    @Override
    public Local buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idlocal, descripcion from local where idlocal=?",
                    new BeanPropertyRowMapper<Local>(Local.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Local> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select id,nombre,correo,password from local",
                new BeanPropertyRowMapper<Local>(Local.class));
    }

    @Override
    public Local buscar(String descripcion) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idlocal, descripcion from local where descripcion=?",
                    new BeanPropertyRowMapper<Local>(Local.class), descripcion);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Local> buscarPorNombre(String nombre) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("nombre","%"+nombre+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from local where descripcion like :nombre",
                    new BeanPropertyRowMapper<Local>(Local.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}