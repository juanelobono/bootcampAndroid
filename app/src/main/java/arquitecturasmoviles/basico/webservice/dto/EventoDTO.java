package arquitecturasmoviles.basico.webservice.dto;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arquitecturasmoviles.basico.model.Evento;

@Parcel
public class EventoDTO implements Serializable{

    @SerializedName("id")
    protected long id;

    @SerializedName("nombre")
    protected String nombre;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("fecha_inicio")
    private Date inicio;

    @SerializedName("fecha_fin")
    private Date fin;

    @SerializedName("lugar")
    private String lugar;


    public EventoDTO(long id, String nombre, String descripcion, Date fechaInicio, Date fechaFin, String lugar){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inicio = fechaInicio;
        this.fin = fechaFin;
        this.lugar = lugar;
    }

    public EventoDTO(){}

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

    public void setInicio(Date fechaInicio) {
        this.inicio = fechaInicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fechaFin) {
        this.fin = fechaFin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Evento getModel(){
        Evento evento = new Evento();
        evento.setId(id);
        evento.setDescripcion(descripcion);
        evento.setLugar(lugar);
        evento.setNombre(nombre);
        evento.setInicio(inicio);
        evento.setFin(fin);
        return evento;
    }
}
