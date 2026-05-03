package pe.edu.upc.wasiseguro.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_incidente")
public class TipoIncidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 80, nullable = false, unique = true)
    private String nombre;

    @Column(name = "icono_url")
    private String iconoUrl;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    public TipoIncidente() {
    }

    public TipoIncidente(int id, String nombre, String iconoUrl, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.iconoUrl = iconoUrl;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIconoUrl() {
        return iconoUrl;
    }

    public void setIconoUrl(String iconoUrl) {
        this.iconoUrl = iconoUrl;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
