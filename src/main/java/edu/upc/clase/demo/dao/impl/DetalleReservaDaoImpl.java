package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.DetalleReservaDao;
import edu.upc.clase.demo.entity.DetalleReserva;
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
public class DetalleReservaDaoImpl extends SimpleJdbcDaoSupport implements DetalleReservaDao {

    private static Logger log = LoggerFactory.getLogger(DetalleReservaDaoImpl.class);

    @Autowired
    public DetalleReservaDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(DetalleReserva detallearmado) {

        getJdbcTemplate().update(
                "insert into detallereserva (idinstrumento,idreserva) values (?, ?)",
                detallearmado.getIdinstrumento(), detallearmado.getIdreserva());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }


    @Override
    public void eliminar(DetalleReserva detallereserva) {
        getJdbcTemplate().update(
                "delete from detallereserva where iddetallereserva = ?", detallereserva.getIddetallereserva());
    }

    @Override
    public DetalleReserva buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select iddetallereserva,idinstrumento,idreserva  from detallereserva where iddetallereserva=?",
                    new BeanPropertyRowMapper<DetalleReserva>(DetalleReserva.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<DetalleReserva> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select iddetallereserva,idinstrumento,idreserva  from detallereserva",
                new BeanPropertyRowMapper<DetalleReserva>(DetalleReserva.class));
    }

//    
}