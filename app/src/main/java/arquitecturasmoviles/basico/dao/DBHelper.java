package arquitecturasmoviles.basico.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends  SQLiteOpenHelper {

    public static final String DB_NAME = "basico.db";
    public static final int DB_VERSION = 1;
    private static String DB_PATH = "/data/data/arquitecturasmoviles.basico/databases/";
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventoDAO.CREAR);
        db.execSQL(CursoDAO.CREAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EventoDAO.TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + CursoDAO.TABLA);
        onCreate(db);
    }

}
