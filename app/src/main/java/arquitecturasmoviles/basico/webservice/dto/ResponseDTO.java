package arquitecturasmoviles.basico.webservice.dto;

import java.util.ArrayList;

public class ResponseDTO {

    private boolean error;
    private String mensaje;
    private String userid;
    private String token;
    private ArrayList<EventoDTO> eventos;
    private ArrayList<CursoDTO> cursos;

    public boolean isError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getUserid() {
        return userid;
    }

    public String getToken() {
        return token;
    }

    public ArrayList<EventoDTO> getEventos() {
        return eventos;
    }

    public ArrayList<CursoDTO> getCursos() {
        return cursos;
    }
}

