package arquitecturasmoviles.basico.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import arquitecturasmoviles.basico.R;
import arquitecturasmoviles.basico.adapter.ListaEventosAdapter;
import arquitecturasmoviles.basico.dao.CursoDAO;
import arquitecturasmoviles.basico.dao.EventoDAO;
import arquitecturasmoviles.basico.model.Curso;
import arquitecturasmoviles.basico.model.Evento;
import arquitecturasmoviles.basico.webservice.ApiService;
import arquitecturasmoviles.basico.webservice.dto.CursoDTO;
import arquitecturasmoviles.basico.webservice.dto.EventoDTO;
import arquitecturasmoviles.basico.webservice.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventosActivity extends AppCompatActivity {

    private ListView lstEventos;
    private ListaEventosAdapter listaEventosAdapter;
    private List<Evento> eventos;

    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    private EventoDAO eventoDAO = null;
    private CursoDAO cursoDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        lstEventos = (ListView) findViewById(R.id.lst_eventos);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        linearLayout = (LinearLayout) findViewById(R.id.vista_eventos);

        eventoDAO = new EventoDAO(getApplicationContext());
        cursoDAO = new CursoDAO(getApplicationContext());

        eventos = new ArrayList<>();


        listaEventosAdapter = new ListaEventosAdapter(eventos, this);
        lstEventos.setAdapter(listaEventosAdapter);

        lstEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                Evento eventoDTOSeleccionado =
                        (Evento) listaEventosAdapter.getItem(posicion);
                Intent intent = new Intent(getApplicationContext(), CursosActivity.class);
                intent.putExtra("evento", eventoDTOSeleccionado);
                startActivity(intent);
            }
        });

        mostrarProgress(true);

        obtenerEventos();
    }

    private void mostrarProgress(boolean mostrar){
        progressBar.setVisibility(mostrar ? View.VISIBLE : View.GONE);
        linearLayout.setVisibility(mostrar ? View.GONE : View.VISIBLE);
    }


    private void obtenerEventos(){
        ApiService.Factory.getInstance(this).getEventos().enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                eventoDAO.limpiarTabla();
                ArrayList<EventoDTO> eventosDTO = response.body().getEventos();
                for (EventoDTO eventoDTO : eventosDTO){
                    Evento e = eventoDTO.getModel();
                    eventos.add(e);
                    eventoDAO.insertar(e);
                }
                eventos = eventoDAO.obtenerTodos();
                listaEventosAdapter.actualizarEventos(eventos);
                obtenerCursos();
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

            }
        });
    }

    private void obtenerCursos(){
        ApiService.Factory.getInstance(this).getCursos().enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                cursoDAO.limpiarTabla();
                ArrayList<CursoDTO> cursosDTO = response.body().getCursos();
                for(CursoDTO cursoDTO : cursosDTO){
                    Curso c = cursoDTO.getModel();
                    cursoDAO.insertar(c);
                }
                mostrarProgress(false);
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                mostrarProgress(false);
            }
        });
    }
}
