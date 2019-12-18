package mx.com.aft.model.entities;

public class TipoDocumento implements java.io.Serializable {

    private int idTipoDocumento;
    private String nombre;
    private String descripcion;
    private String extension;
    private String mimetype;

    public TipoDocumento() {
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" + "idTipoDocumento=" + idTipoDocumento + ", nombre=" + nombre + ", descripcion=" + descripcion + ", extension=" + extension + ", mimetype=" + mimetype + '}';
    }

}
