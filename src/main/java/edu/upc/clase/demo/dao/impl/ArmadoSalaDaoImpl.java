package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.ArmadoSalaDao;
import edu.upc.clase.demo.entity.ArmadoSala;
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
public class ArmadoSalaDaoImpl extends SimpleJdbcDaoSupport implements ArmadoSalaDao {

    private static Logger log = LoggerFactory.getLogger(ArmadoSalaDaoImpl.class);

    @Autowired
    public ArmadoSalaDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(ArmadoSala armadosala) {

        getJdbcTemplate().update(
                "insert into armadosala (idsala,costo) values (?, ?)",
                armadosala.getIdsala(), armadosala.getCosto());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }

    @Override
    public void actualizar(ArmadoSala armadosala) {
        getJdbcTemplate().update(
                "update armadosala set costo = ? where idarmadosala = ?",
                armadosala.getCosto(), armadosala.getIdarmadosala());
    }

    @Override
    public void eliminar(ArmadoSala armadosala) {
        getJdbcTemplate().update(
                "delete from armadosala where idarmadosala = ?", armadosala.getIdarmadosala());
    }

    @Override
    public ArmadoSala buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idarmadosala,idsala,costo from armadosala where idarmadosala=?",
                    new BeanPropertyRowMapper<ArmadoSala>(ArmadoSala.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public ArmadoSala buscar(String correo) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select id, nombre, correo,password from armadosala where costo=?",
                    new BeanPropertyRowMapper<ArmadoSala>(ArmadoSala.class), correo);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<ArmadoSala> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select idarmadosala,idsala,costo from armadosala",
                new BeanPropertyRowMapper<ArmadoSala>(ArmadoSala.class));
    }



    @Override
    public List<ArmadoSala> buscarPorNombre(String nombre) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("nombre","%"+nombre+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from armadosala where nombre like :nombre",
                    new BeanPropertyRowMapper<ArmadoSala>(ArmadoSala.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}