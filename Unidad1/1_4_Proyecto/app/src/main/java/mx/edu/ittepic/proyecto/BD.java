package mx.edu.ittepic.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class BD extends SQLiteOpenHelper {

    private	static final int VERSION =	1;
    private	static final String	BD = "Alumnos";
    private	static final String TABLA = "Alumno";

    private static final String ID = "Id";
    private static final	String NOMBRE = "Nombre";
    private static final	String CARRERA = "Carrera";
    private static final	String NOCONTROL = "NoControl";

    public BD(Context context) {
        super(context, BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_ALUMNO_TABLE = "CREATE	 TABLE " + TABLA + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOMBRE + " TEXT," + CARRERA + " TEXT," + NOCONTROL + " TEXT" + ")";
        db.execSQL(CREATE_ALUMNO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA);
        onCreate(db);
    }

    public List<Alumno> alumnos(){
        String sql = "select * from " + TABLA;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Alumno> alumnos = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String nombre = cursor.getString(1);
                String carrera = cursor.getString(2);
                String noControl = cursor.getString(3);

                alumnos.add(new Alumno(id, nombre, carrera,noControl));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return alumnos;
    }

    public void addAlumno(Alumno alumno){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor cursor = db.rawQuery("SELECT MAX(Id) FROM Alumno",	null);

        values.put( ID, String.valueOf( cursor ) );*/
        values.put( ID, alumno.getId() );
        values.put(NOMBRE, alumno.getNombre());
        values.put(CARRERA, alumno.getCarrera());
        values.put(NOCONTROL, alumno.getNoControl());
        db.insert(TABLA, null, values);
    }

    public void updateAlumno(Alumno alumno){
        ContentValues values = new ContentValues();
        values.put(NOMBRE, alumno.getNombre());
        values.put(CARRERA, alumno.getCarrera());
        values.put(NOCONTROL, alumno.getNoControl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLA, values, ID	+ "	= ?", new String[] { String.valueOf(alumno.getId())});
    }

    public void deleteAlumno(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA, ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
