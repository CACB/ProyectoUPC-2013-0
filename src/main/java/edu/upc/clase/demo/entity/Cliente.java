/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.clase.demo.entity;

/**
 *
 * @author PAPA
 */
public class Cliente {
    
    private int idcliente;
    private String nombre;
    private String apepat;
    private String apemat;
    private String email;
    private String fecha;
    private String documento;
    private String usuario;
    private String clave;
    private String confirmar;

    public Cliente() {
    }

    public Cliente(String nombre, String apepat, String apemat, String email, String fecha, String documento, String usuario, String clave, String confirmar) {        
        this.nombre = nombre;
        this.apepat = apepat;
        this.apemat = apemat;
        this.email = email;
        this.fecha = fecha;
        this.documento = documento;
        this.usuario = usuario;
        this.clave = clave;
        this.confirmar = confirmar;
    }

    public String getApemat() {
        return apemat;
    }

    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    public String getApepat() {
        return apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConfirmar() {
        return confirmar;
    }

    public void setConfirmar(String confirmar) {
        this.confirmar = confirmar;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
