package mx.edu.ittepic.cursos;

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
    private	static final String	BD = "Personas";
    private	static final String TABLA = "Persona";

    private static final String ID = "Id";
    private static final	String CLAVE = "Clave";
    private static final	String NOMBRE = "Nombre";
    private static final	String SALARIO = "Salario";

    public BD(Context context) {
        super(context, BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_ALUMNO_TABLE = "CREATE	 TABLE " + TABLA + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CLAVE + " TEXT," + NOMBRE + " TEXT," + SALARIO + " TEXT" + ")";
        db.execSQL(CREATE_ALUMNO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA);
        onCreate(db);
    }

    public List<Persona> personas(){
        String sql = "select * from " + TABLA;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Persona> personas = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String clave = cursor.getString(1);
                String nombre = cursor.getString(2);
                String salario = cursor.getString(3);

                personas.add(new Persona(id, clave, nombre,salario));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return personas;
    }

    public void addPersona(Persona persona){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor cursor = db.rawQuery("SELECT MAX(Id) FROM Persona",	null);

        values.put( ID, String.valueOf( cursor ) );*/
        values.put( ID, persona.getId() );
        values.put(CLAVE, persona.getClave());
        values.put(NOMBRE, persona.getNombre());
        values.put(SALARIO, persona.getSalario());
        db.insert(TABLA, null, values);
    }

    public void updatePersona(Persona persona){
        ContentValues values = new ContentValues();
        values.put(CLAVE, persona.getClave());
        values.put(NOMBRE, persona.getNombre());
        values.put(SALARIO, persona.getSalario());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLA, values, ID	+ "	= ?", new String[] { String.valueOf(persona.getId())});
    }

    public void deletePersona(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA, ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
