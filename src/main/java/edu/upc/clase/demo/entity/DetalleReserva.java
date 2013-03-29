package edu.upc.clase.demo.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gian
 */
@XmlRootElement
public class DetalleReserva implements Serializable {
    
   private int iddetallereserva;
   private int idinstrumento;
   private int idreserva;

    public DetalleReserva() {
    }

    public DetalleReserva(int idinstrumento, int idreserva) {
        this.idinstrumento = idinstrumento;
        this.idreserva = idreserva;
    }

    public int getIddetallereserva() {
        return iddetallereserva;
    }

    public void setIddetallereserva(int iddetallereserva) {
        this.iddetallereserva = iddetallereserva;
    }

    public int getIdinstrumento() {
        return idinstrumento;
    }

    public void setIdinstrumento(int idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }
      
}