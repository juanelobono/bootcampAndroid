package arquitecturasmoviles.basico.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import arquitecturasmoviles.basico.model.Curso;
import arquitecturasmoviles.basico.webservice.dto.CursoDTO;

public class CursoDAO extends DBHelper{

    private SQLiteDatabase basedatos;
    public static final String TABLA = "CURSO";

    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String FECHA = "fecha";
    public static final String DESCRIPCION = "descripcion";
    public static final String DURACION = "duracion";
    public static final String DISERTANTE = "disertante";
    public static final String ASISTIR = "asistir";
    public static final String EVENTO_ID = "evento_id";

    public static final int ID_INDEX = 0;
    public static final int NOMBRE_INDEX = 1;
    public static final int FECHA_INDEX = 2;
    public static final int DESCRIPCION_INDEX = 3;
    public static final int DURACION_INDEX = 4;
    public static final int DISERTANTE_INDEX = 5;
    public static final int ASISTIR_INDEX = 6;
    public static final int EVENTO_ID_INDEX = 7;

    public static final String CREAR = "CREATE TABLE " + TABLA + " ("
            + ID + " INTEGER PRIMARY KEY NOT NULL, "
            + NOMBRE + " TEXT, "
            + FECHA + " TEXT, "
            + DESCRIPCION + " TEXT, "
            + DURACION + " INTEGER, "
            + DISERTANTE + " TEXT, "
            + ASISTIR + " INTEGER DEFAULT 0, "
            + EVENTO_ID + " INTEGER, "
            + "FOREIGN KEY(" + EVENTO_ID + ") REFERENCES " + EventoDAO.TABLA + "(" + EventoDAO.ID + ")"
            + ")";

    public CursoDAO(Context context) {
        super(context);
        this.basedatos = getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }

    public void insertar(Curso curso){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, curso.getId());
        contentValues.put(NOMBRE, curso.getNombre());
        contentValues.put(FECHA, curso.getFecha());
        contentValues.put(DESCRIPCION, curso.getDescripcion());
        contentValues.put(DURACION, curso.getDuracion());
        contentValues.put(DISERTANTE, curso.getDisertante());
        contentValues.put(ASISTIR, curso.estoyRegistrado());
        contentValues.put(EVENTO_ID, curso.getEventoId());

        abrirDB();
        basedatos.insert(TABLA, null, contentValues);
        cerrarDB();
    }

    public List<Curso> obtenerTodos(){
        String consulta = "SELECT  * FROM " + TABLA;
        abrirDB();
        Cursor cursor = basedatos.rawQuery(consulta, null);

        ArrayList<Curso> cursos = new ArrayList<Curso>();

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Curso curso = new Curso();
                curso.setId(cursor.getLong(ID_INDEX));
                curso.setNombre(cursor.getString(NOMBRE_INDEX));
                curso.setFecha(cursor.getString(FECHA_INDEX));
                curso.setDescripcion(cursor.getString(DESCRIPCION_INDEX));
                curso.setDuracion(cursor.getShort(DURACION_INDEX));
                curso.setDisertante(cursor.getString(DISERTANTE_INDEX));
                curso.setAsistir(cursor.getInt(ASISTIR_INDEX) == 1);
                cursos.add(curso);
                cursor.moveToNext();
            }
        }
        cursor.close();
        cerrarDB();

        return cursos;
    }

    public List<Curso> obtenerPorEvento(long idEvento){
        String consulta = "SELECT  * FROM " + TABLA + " WHERE " + EVENTO_ID + " = " + idEvento;
        abrirDB();
        Cursor cursor = basedatos.rawQuery(consulta, null);

        ArrayList<Curso> cursos = new ArrayList<Curso>();

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Curso curso = new Curso();
                curso.setId(cursor.getLong(ID_INDEX));
                curso.setNombre(cursor.getString(NOMBRE_INDEX));
                curso.setFecha(cursor.getString(FECHA_INDEX));
                curso.setDescripcion(cursor.getString(DESCRIPCION_INDEX));
                curso.setDuracion(cursor.getShort(DURACION_INDEX));
                curso.setDisertante(cursor.getString(DISERTANTE_INDEX));
                curso.setAsistir(cursor.getInt(ASISTIR_INDEX) == 1);
                cursos.add(curso);
                cursor.moveToNext();
            }
        }
        cursor.close();
        cerrarDB();

        return cursos;
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
