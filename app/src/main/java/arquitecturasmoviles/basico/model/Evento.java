package arquitecturasmoviles.basico.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento implements Serializable {

    private long id;
    private String nombre;
    private String descripcion;
    private Date inicio;
    private Date fin;
    private String lugar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFechaInicio(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(inicio);
    }

    public long getFechaInicioMS(){
        return inicio.getTime();
    }

    public String getFechaFin(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fin);
    }

    public long getFechaFinMS(){
        return fin.getTime();
    }

    public String getFechaInicioPorPatron(String patron){
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        return sdf.format(inicio);
    }

    public String getFechaFinPorPatron(String patron){
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        return sdf.format(fin);
    }
}
