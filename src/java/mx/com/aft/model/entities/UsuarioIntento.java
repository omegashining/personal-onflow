package mx.com.aft.model.entities;

import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class UsuarioIntento implements java.io.Serializable {
    
    public int idUsuarioIntento;
    public String username;
    public int intentos;
    public Date modificado;

    public int getIdUsuarioIntento() {
        return idUsuarioIntento;
    }

    public void setIdUsuarioIntento(int idUsuarioIntento) {
        this.idUsuarioIntento = idUsuarioIntento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "UsuarioIntento{" + "idUsuarioIntento=" + idUsuarioIntento + ", username=" + username + ", intentos=" + intentos + ", modificado=" + modificado + '}';
    }
    
}
