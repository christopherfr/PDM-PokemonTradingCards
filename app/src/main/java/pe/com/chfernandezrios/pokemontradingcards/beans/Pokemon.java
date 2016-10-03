package pe.com.chfernandezrios.pokemontradingcards.beans;

/**
 * Created by chfernandezrios on 2/10/2016.
 */
public class Pokemon {
    private int id;
    private String url;
    private String nombre;
    private String tipo;
    private int nivel;
    private String descripcion;

    public Pokemon(int id, String url, String nombre, String tipo, int nivel, String descripcion) {
        this.id = id;
        this.url = url;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
