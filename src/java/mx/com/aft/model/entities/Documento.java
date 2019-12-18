package mx.com.aft.model.entities;

public class Documento implements java.io.Serializable {

    private int idDocumento;
    private String nombre;
    private byte[] contenido;
    private TipoDocumento tipoDocumento;

    public Documento() {
        this.tipoDocumento = new TipoDocumento();
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public String toString() {
        return "Documento{" + "idDocumento=" + idDocumento + ", nombre=" + nombre + ", contenido=" + contenido + ", tipoDocumento=" + tipoDocumento + '}';
    }

}
