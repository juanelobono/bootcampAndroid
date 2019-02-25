package arquitecturasmoviles.basico.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arquitecturasmoviles.basico.R;
import arquitecturasmoviles.basico.adapter.ListaCursosAdapter;
import arquitecturasmoviles.basico.dao.CursoDAO;
import arquitecturasmoviles.basico.model.Curso;
import arquitecturasmoviles.basico.model.Evento;
import arquitecturasmoviles.basico.webservice.dto.CursoDTO;

public class CursosActivity extends AppCompatActivity {
    private Evento evento;
    private List<Curso> cursos;
    private ListaCursosAdapter listaCursosAdapter;
    private CursoDAO cursoDAO;

    private ListView lstCursos;
    private TextView txtNombreEvento;
    private TextView txtFechaEvento;
    private TextView txtLugarEvento;
    private TextView txtDescripcionEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        Bundle bundle = getIntent().getExtras();
        evento = (Evento) bundle.get("evento");

        cursos = new ArrayList<>();
        cursoDAO = new CursoDAO(getApplicationContext());

        cursos = cursoDAO.obtenerPorEvento(evento.getId());

        lstCursos = (ListView) findViewById(R.id.lst_cursos);
        txtNombreEvento = (TextView) findViewById(R.id.txt_cursos_nombre_evento);
        txtFechaEvento = (TextView) findViewById(R.id.txt_cursos_fecha_evento);
        txtLugarEvento = (TextView) findViewById(R.id.txt_cursos_lugar_evento);
        txtDescripcionEvento = (TextView) findViewById(R.id.txt_cursos_descripcion_evento);

        txtNombreEvento.setText(evento.getNombre());
        String fechaEvento = String.format("Del %s al %s", evento.getFechaInicioPorPatron("dd/MM"), evento.getFechaFinPorPatron("dd/MM"));
        txtFechaEvento.setText(fechaEvento);
        txtLugarEvento.setText(evento.getLugar());
        txtDescripcionEvento.setText(evento.getDescripcion());

        txtDescripcionEvento.setMovementMethod(new ScrollingMovementMethod());

        listaCursosAdapter = new ListaCursosAdapter(cursos, this);
        lstCursos.setAdapter(listaCursosAdapter);

        lstCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CursoDTO cursoDTO = (CursoDTO) listaCursosAdapter.getItem(i);
                Toast.makeText(getApplicationContext(), cursoDTO.getNombre(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
