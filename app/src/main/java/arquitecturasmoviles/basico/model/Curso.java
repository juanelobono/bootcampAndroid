package arquitecturasmoviles.basico.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Curso {
    protected long id;
    protected String nombre;
    private Date fecha;
    private String descripcion;
    private short duracion;
    private String disertante;
    private long eventoId;
    private boolean asistir;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public long getEventoId() {
        return eventoId;
    }

    public void setEventoId(long eventoId) {
        this.eventoId = eventoId;
    }

    public boolean estoyRegistrado(){
        return asistir;
    }

    public void registrarme(){
        this.asistir = true;
    }

    public void noRegistrarme(){
        this.asistir = true;
    }

    public void setAsistir(boolean asistir){
        this.asistir = asistir;
    }

    public String getDia(String patron) {
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        return sdf.format(fecha);
    }

    public String getHora(String patron) {
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        return sdf.format(fecha);
    }

    public long getFechaMS(){
        return fecha.getTime();
    }
}
