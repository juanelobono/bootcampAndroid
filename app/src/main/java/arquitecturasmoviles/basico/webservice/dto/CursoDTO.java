package arquitecturasmoviles.basico.webservice.dto;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;

import arquitecturasmoviles.basico.model.Curso;

@Parcel
public class CursoDTO {

    @SerializedName("id")
    protected long id;

    @SerializedName("nombre")
    protected String nombre;

    @SerializedName("dia_hora")
    private String fecha;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("duracion")
    private short duracion;

    @SerializedName("disertante")
    private String disertante;

    @SerializedName("evento_id")
    private long eventoId;


    public CursoDTO(long id, String nombre, String fecha, String descripcion, short duracion, String disertante) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.disertante = disertante;
    }

    public CursoDTO(){}

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

    public short getDuracion() {
        return duracion;
    }

    public void setDuracion(short duracion) {
        this.duracion = duracion;
    }

    public String getDisertante() {
        return disertante;
    }

    public void setDisertante(String disertante) {
        this.disertante = disertante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getEventoId() {
        return eventoId;
    }

    public void setEventoId(long eventoId) {
        this.eventoId = eventoId;
    }

    public Curso getModel(){
        Curso curso = new Curso();
        curso.setId(id);
        curso.setDescripcion(descripcion);
        curso.setDisertante(disertante);
        curso.setDuracion(duracion);
        curso.setEventoId(eventoId);
        curso.setFecha(fecha);
        curso.setNombre(nombre);
        return curso;
    }
}
