package mx.edu.ittepic.laboratorio2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class BD extends SQLiteOpenHelper {

    private	static final int VERSION =	1;
    private	static final String	BD = "Multa";
    private	static final String TABLA = "Multa";

    private static final String ID = "Id";
    private static final String NUMERO = "Numero";
    private static final String CELULAR = "Celular";
    private static final String CORREO = "Correo";
    private static final String MONTO = "Monto";
    private static final String PUNTOS = "Puntos";

    public BD(Context context) {
        super(context, BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREAR_TABLA = "CREATE	 TABLE " + TABLA + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NUMERO + " INTEGER," + CELULAR + " TEXT," + CORREO + " TEXT," + MONTO + " INTEGER,"
                + PUNTOS + " INTEGER" + ")";
        db.execSQL(CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA);
        onCreate(db);
    }

    public List<Multa> multas(){
        String sql = "select * from " + TABLA;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Multa> multas = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                int numero = Integer.parseInt(cursor.getString(1));
                String celular = cursor.getString(2);
                String correo = cursor.getString(3);
                int monto = Integer.parseInt(cursor.getString(4));
                int puntos = Integer.parseInt(cursor.getString(5));

                multas.add(new Multa(id, numero, celular, correo, monto,puntos));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return multas;
    }

    public void addMulta(Multa multa){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor cursor = db.rawQuery("SELECT MAX(Id) FROM Multa",	null);

        values.put( ID, String.valueOf( cursor ) );*/
        values.put( ID, multa.getId() );
        values.put(NUMERO, multa.getNumero());
        values.put(CELULAR, multa.getCelular());
        values.put(CORREO, multa.getCorreo());
        values.put(MONTO, multa.getMonto());
        values.put(PUNTOS, multa.getPuntos());
        db.insert(TABLA, null, values);
    }

    public String montoTotal(int numero){
        int total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT SUM("+MONTO+") AS SUMA FROM "+TABLA+" WHERE " +NUMERO +"="+numero;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt( cursor.getColumnIndex( "SUMA" ) );
        }
        cursor.close();
        return String.valueOf(total);
    }
    public String puntosTotales(int numero){
        int total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT SUM("+PUNTOS+") AS SUMA FROM "+TABLA+" WHERE " +NUMERO +"="+numero;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt( cursor.getColumnIndex( "SUMA" ) );
        }
        cursor.close();
        return String.valueOf(total);
    }

    public void updateMulta(Multa multa){
        ContentValues values = new ContentValues();
        values.put(NUMERO, multa.getNumero());
        values.put(CELULAR, multa.getCelular());
        values.put(CORREO, multa.getCorreo());
        values.put(MONTO, multa.getMonto());
        values.put(PUNTOS, multa.getPuntos());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLA, values, ID	+ "	= ?", new String[] { String.valueOf( multa.getId())});
    }

    public void deleteMulta(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLA, ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
