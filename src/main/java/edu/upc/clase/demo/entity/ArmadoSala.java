package edu.upc.clase.demo.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gian
 */
@XmlRootElement
public class ArmadoSala implements Serializable {
   
    private int idarmadosala;
    private int idsala;
    private double costo;

    public ArmadoSala() {
    }

    public ArmadoSala(int idsala, double costo) {
        this.idsala = idsala;
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getIdarmadosala() {
        return idarmadosala;
    }

    public void setIdarmadosala(int idarmadosala) {
        this.idarmadosala = idarmadosala;
    }

    public int getIdsala() {
        return idsala;
    }

    public void setIdsala(int idsala) {
        this.idsala = idsala;
    }
    
    
   
}