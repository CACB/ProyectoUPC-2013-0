package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.InstrumentoDao;
import edu.upc.clase.demo.entity.Instrumento;
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
public class InstrumentoDaoImpl extends SimpleJdbcDaoSupport implements InstrumentoDao {

    private static Logger log = LoggerFactory.getLogger(InstrumentoDaoImpl.class);

    @Autowired
    public InstrumentoDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(Instrumento instrumento) {

        getJdbcTemplate().update(
                "insert into instrumento (tipo,marca,modelo,aniofabricacion,caracteristica,costo) values (?, ?, ?, ?, ?, ?)",
                instrumento.getTipo(), instrumento.getMarca(), instrumento.getModelo(),instrumento.getAniofabricacion(),instrumento.getCaracteristicas(),instrumento.getCosto());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }

    @Override
    public void actualizar(Instrumento instrumento) {
        getJdbcTemplate().update(
                "update instrumento set tipo = ?, marca = ?, modelo = ?, aniofabricacion = ?, caracteristica = ?, costo = ? where idinstrumento = ?",
                instrumento.getTipo(), instrumento.getMarca(), instrumento.getModelo(),instrumento.getAniofabricacion(),instrumento.getCaracteristicas(),instrumento.getCosto(), instrumento.getIdinstrumento());
    }

    @Override
    public void eliminar(Instrumento instrumento) {
        getJdbcTemplate().update(
                "delete from instrumento where idinstrumento = ?", instrumento.getIdinstrumento());
    }

    @Override
    public Instrumento buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idinstrumento, tipo, marca,modelo from instrumento where idinstrumento=?",
                    new BeanPropertyRowMapper<Instrumento>(Instrumento.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Instrumento> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select idinstrumento, tipo, marca,modelo from instrumento",
                new BeanPropertyRowMapper<Instrumento>(Instrumento.class));
    }

    @Override
    public Instrumento buscar(String marca) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idinstrumento, tipo, marca,modelo from instrumento where marca=?",
                    new BeanPropertyRowMapper<Instrumento>(Instrumento.class), marca);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Instrumento> buscarPorNombre(String tipo) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("nombre","%"+tipo+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from instrumento where tipo like tipo",
                    new BeanPropertyRowMapper<Instrumento>(Instrumento.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}