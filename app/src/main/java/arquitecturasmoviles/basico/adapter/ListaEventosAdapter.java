package arquitecturasmoviles.basico.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import arquitecturasmoviles.basico.model.Evento;
import arquitecturasmoviles.basico.webservice.dto.EventoDTO;

import arquitecturasmoviles.basico.R;

public class ListaEventosAdapter extends BaseAdapter{

    private List<Evento> eventos;
    private Context contexto;

    public ListaEventosAdapter(List<Evento> eventos, Context contexto) {
        this.eventos = eventos;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int indice) {
        return eventos.get(indice);
    }

    @Override
    public long getItemId(int indice) {
        return eventos.get(indice).getId();
    }

    static class ViewHolder{
        TextView nombreEvento;
        TextView fechaEvento;
        TextView lugarEvento;
    }

    public void actualizarEventos(List<Evento> eventos){
        this.eventos = eventos;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int indice, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(contexto).inflate(R.layout.item_lista_eventos, null);

            viewHolder.nombreEvento = (TextView) view.findViewById(R.id.txt_nombre_evento);
            viewHolder.fechaEvento = (TextView) view.findViewById(R.id.txt_fecha_evento);
            viewHolder.lugarEvento = (TextView) view.findViewById(R.id.txt_lugar_evento);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Evento evento = (Evento) getItem(indice);
        viewHolder.nombreEvento.setText(evento.getNombre());
        String fechaEvento = String.format("Del %s al %s", evento.getFechaInicioPorPatron("dd/MM"), evento.getFechaFinPorPatron("dd/MM"));
        viewHolder.fechaEvento.setText(fechaEvento);
        viewHolder.lugarEvento.setText(evento.getLugar());
        return view;
    }
}
