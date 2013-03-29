package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.DetalleArmadoDao;
import edu.upc.clase.demo.entity.DetalleArmado;
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
public class DetalleArmadoDaoImpl extends SimpleJdbcDaoSupport implements DetalleArmadoDao {

    private static Logger log = LoggerFactory.getLogger(DetalleArmadoDaoImpl.class);

    @Autowired
    public DetalleArmadoDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(DetalleArmado detallearmado) {

        getJdbcTemplate().update(
                "insert into detallearmado (idinstrumento,idarmadosala) values (?, ?)",
                detallearmado.getIdinstrumento(), detallearmado.getIdsala());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }

//    @Override
//    public void actualizar(DetalleArmado detallearmado) {
//        getJdbcTemplate().update(
////                "update detallearmado set nombre = ?, correo = ? where id = ?",
////                detallearmado.getNombre(), detallearmado.getCorreo(), detallearmado.getId());
//    }

    @Override
    public void eliminar(DetalleArmado detallearmado) {
        getJdbcTemplate().update(
                "delete from detallearmado where iddetallearmado = ?", detallearmado.getIddetallearmado());
    }

    @Override
    public DetalleArmado buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select iddetallearmado,idinstrumento,idarmadosala  from detallearmado where iddetallearmado=?",
                    new BeanPropertyRowMapper<DetalleArmado>(DetalleArmado.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<DetalleArmado> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select iddetallearmado,idinstrumento,idarmadosala from detallearmado",
                new BeanPropertyRowMapper<DetalleArmado>(DetalleArmado.class));
    }

//    @Override
//    public DetalleArmado buscar(String correo) {
//        try {
//            return getSimpleJdbcTemplate().queryForObject(
//                    "select iddetallearmado,idinstrumento,idarmadosala from detallearmado where correo=?",
//                    new BeanPropertyRowMapper<DetalleArmado>(DetalleArmado.class), correo);
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }

//    @Override
//    public List<DetalleArmado> buscarPorNombre(String nombre) {
//        try {
//            Map<String,String> parametros = new HashMap<String,String>();
//            parametros.put("nombre","%"+nombre+"%");
//            return getSimpleJdbcTemplate().query(
//                    "select * from detallearmado where nombre like :nombre",
//                    new BeanPropertyRowMapper<DetalleArmado>(DetalleArmado.class),parametros);
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }
}