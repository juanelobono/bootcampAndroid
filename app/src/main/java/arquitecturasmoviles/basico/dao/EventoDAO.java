package arquitecturasmoviles.basico.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import arquitecturasmoviles.basico.model.Evento;
import arquitecturasmoviles.basico.webservice.dto.EventoDTO;

public class EventoDAO extends DBHelper{

    private SQLiteDatabase basedatos;
    public static final String TABLA = "EVENTO";

    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String FECHA_INICIO = "fecha_inicio";
    public static final String FECHA_FIN = "fecha_fin";
    public static final String LUGAR = "lugar";

    public static final int ID_INDEX = 0;
    public static final int NOMBRE_INDEX = 1;
    public static final int DESCRIPCION_INDEX = 2;
    public static final int FECHA_INICIO_INDEX = 3;
    public static final int FECHA_FIN_INDEX = 4;
    public static final int LUGAR_INDEX = 5;

    public static final String CREAR = "CREATE TABLE " + TABLA + " ("
            + ID + " INTEGER PRIMARY KEY NOT NULL, "
            + NOMBRE + " TEXT, "
            + DESCRIPCION + " TEXT, "
            + FECHA_INICIO + " INTEGER, "
            + FECHA_FIN + " INTEGER, "
            + LUGAR + " TEXT"
            + ")";

    public EventoDAO(Context context) {
        super(context);
        this.basedatos = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }

    public void insertar(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, evento.getId());
        contentValues.put(NOMBRE, evento.getNombre());
        contentValues.put(FECHA_INICIO, evento.getFechaInicioMS());
        contentValues.put(FECHA_FIN, evento.getFechaFinMS());
        contentValues.put(DESCRIPCION, evento.getDescripcion());
        contentValues.put(LUGAR, evento.getLugar());

        abrirDB();
        basedatos.insert(TABLA, null, contentValues);
        cerrarDB();
    }

    public List<Evento> obtenerTodos(){
        String consulta = "SELECT  * FROM " + TABLA;
        abrirDB();
        Cursor cursor = basedatos.rawQuery(consulta, null);

        ArrayList<Evento> eventos = new ArrayList<Evento>();

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Evento evento = new Evento();
                evento.setId(cursor.getLong(ID_INDEX));
                evento.setNombre(cursor.getString(NOMBRE_INDEX));
                evento.setInicio(new Date(cursor.getLong(FECHA_INICIO_INDEX)));
                evento.setFin(new Date(cursor.getLong(FECHA_FIN_INDEX)));
                evento.setDescripcion(cursor.getString(DESCRIPCION_INDEX));
                evento.setLugar(cursor.getString(LUGAR_INDEX));
                eventos.add(evento);
                cursor.moveToNext();
            }
        }
        cursor.close();
        cerrarDB();

        return eventos;
    }

    public int limpiarTabla(){
        abrirDB();
        int cnt = basedatos.delete(TABLA, null, null);
        cerrarDB();
        return cnt;
    }

    private void abrirDB() {
        if(!basedatos.isOpen()){
            basedatos = getWritableDatabase();
        }
    }

    private void cerrarDB() {
        if(basedatos.isOpen()){
            basedatos.close();
        }
    }
}
