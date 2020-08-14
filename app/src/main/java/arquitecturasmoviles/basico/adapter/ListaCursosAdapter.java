package arquitecturasmoviles.basico.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import arquitecturasmoviles.basico.R;
import arquitecturasmoviles.basico.model.Curso;

public class ListaCursosAdapter extends BaseAdapter{
    private List<Curso> cursos;
    private Context contexto;

    public ListaCursosAdapter(List<Curso> cursos, Context contexto) {
        this.cursos = cursos;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Object getItem(int indice) {
        return cursos.get(indice);
    }

    @Override
    public long getItemId(int indice) {
        return cursos.get(indice).getId();
    }

    static class ViewHolder{
        TextView nombreCurso;
        TextView fechaCurso;
        TextView duracionCurso;
        TextView disertanteCurso;
    }

    @Override
    public View getView(int indice, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(contexto).inflate(R.layout.item_lista_cursos, null);

            viewHolder.nombreCurso = (TextView) view.findViewById(R.id.txt_item_nombre_curso);
            viewHolder.fechaCurso = (TextView) view.findViewById(R.id.txt_item_fecha_curso);
            viewHolder.duracionCurso = (TextView) view.findViewById(R.id.txt_item_duracion_curso);
            viewHolder.disertanteCurso = (TextView) view.findViewById(R.id.txt_item_disertante_curso);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Curso curso = (Curso) getItem(indice);
        viewHolder.nombreCurso.setText(curso.getNombre());
        String fechaCurso = String.format("Fecha: %s  %s", curso.getDia(), curso.getHora());
        viewHolder.fechaCurso.setText(fechaCurso);
        String duracionCurso = String.format("Duración %d min", curso.getDuracion());
        viewHolder.duracionCurso.setText(duracionCurso);
        viewHolder.disertanteCurso.setText(String.format("Disertante: %s", curso.getDisertante()));
        return view;
    }
}
