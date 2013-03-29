package edu.upc.clase.demo.dao.impl;

import edu.upc.clase.demo.dao.ClienteDao;
import edu.upc.clase.demo.entity.Cliente;
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
public class ClienteDaoImpl extends SimpleJdbcDaoSupport implements ClienteDao {

    private static Logger log = LoggerFactory.getLogger(ClienteDaoImpl.class);

    @Autowired
    public ClienteDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(Cliente cliente) {

        getJdbcTemplate().update(
                "insert into cliente (nombre,apepat, apemat,email,fecha,documento,usuario,clave,confirmar) values (?, ?, ?,?, ?, ?,?, ?, ?)",
                cliente.getNombre(), cliente.getApepat(), cliente.getApemat(),cliente.getEmail(),cliente.getFecha(),cliente.getDocumento(),cliente.getUsuario(),cliente.getClave(),cliente.getConfirmar());
        return getSimpleJdbcTemplate().queryForInt("select last_insert_id()");
    }

    @Override
    public void actualizar(Cliente cliente) {
        getJdbcTemplate().update(
                "update cliente set nombre = ?, apepat = ?, apemat = ?, email = ?, fecha = ?, documento = ?, usuario = ?, clave = ?, confirmar = ?  where idcliente = ?",
                cliente.getNombre(), cliente.getApepat(), cliente.getApemat(),cliente.getEmail(),cliente.getFecha(),cliente.getDocumento(),cliente.getUsuario(),cliente.getClave(),cliente.getConfirmar(), cliente.getIdcliente());
    }

    @Override
    public void eliminar(Cliente cliente) {
        getJdbcTemplate().update(
                "delete from cliente where idcliente = ?", cliente.getIdcliente());
    }

    @Override
    public Cliente buscar(Integer id) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idcliente, nombre, email,clave from cliente where idcliente=?",
                    new BeanPropertyRowMapper<Cliente>(Cliente.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select idcliente,nombre,apepat,apemat,email,fecha,usuario from cliente",
                new BeanPropertyRowMapper<Cliente>(Cliente.class));
    }

    @Override
    public Cliente buscar(String correo) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select idcliente,nombre,apepat,apemat,email,fecha,usuario from cliente where email=?",
                    new BeanPropertyRowMapper<Cliente>(Cliente.class), correo);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Cliente> buscarPorNombre(String nombre) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("nombre","%"+nombre+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from cliente where nombre like :nombre",
                    new BeanPropertyRowMapper<Cliente>(Cliente.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}