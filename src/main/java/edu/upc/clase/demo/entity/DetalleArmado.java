package edu.upc.clase.demo.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gian
 */
@XmlRootElement
public class DetalleArmado implements Serializable {
    
   private int iddetallearmado;
   private int idinstrumento;
   private int idsala;

    public DetalleArmado() {
    }

    public DetalleArmado(int idinstrumento, int idsala) {
        this.idinstrumento = idinstrumento;
        this.idsala = idsala;
    }

    public int getIddetallearmado() {
        return iddetallearmado;
    }

    public void setIddetallearmado(int iddetallearmado) {
        this.iddetallearmado = iddetallearmado;
    }

    public int getIdinstrumento() {
        return idinstrumento;
    }

    public void setIdinstrumento(int idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    public int getIdsala() {
        return idsala;
    }

    public void setIdsala(int idsala) {
        this.idsala = idsala;
    }
      
}