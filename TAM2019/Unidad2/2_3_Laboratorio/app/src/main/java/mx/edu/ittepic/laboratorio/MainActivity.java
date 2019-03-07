package mx.edu.ittepic.laboratorio;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BD base;
    private int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView alumnoView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        alumnoView.setLayoutManager(linearLayoutManager);
        alumnoView.setHasFixedSize(true);

        base = new BD(this);
        List<Alumno> alumnos = base.alumnos();
        size = alumnos.size();

        if(alumnos.size() > 0){
            alumnoView.setVisibility(View.VISIBLE);
            AlumnoAdapter adapter = new AlumnoAdapter(this, alumnos);
            alumnoView.setAdapter(adapter);

        }else {
            alumnoView.setVisibility(View.GONE);
            Toast.makeText(this, "No existen Alumnos", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton agregar = findViewById(R.id.agregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAlumno();
            }
        });
    }

    private void agregarAlumno(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_alumno_layout, null);

        final EditText nombreC = subView.findViewById(R.id.nombre);
        final EditText carreraC = subView.findViewById(R.id.carrera);
        final EditText noControlC = subView.findViewById(R.id.noControl);
        final EditText celularC = subView.findViewById(R.id.celular);
        final EditText correoC = subView.findViewById(R.id.correo);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Alumno");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String nombre = nombreC.getText().toString();
                final String carrera = carreraC.getText().toString();
                final String noControl = noControlC.getText().toString();
                final String celular = celularC.getText().toString();
                final String correo = correoC.getText().toString();

                if(TextUtils.isEmpty(nombre) || TextUtils.isEmpty(carrera) || TextUtils.isEmpty(noControl)
                        || TextUtils.isEmpty(celular) || TextUtils.isEmpty(correo)){
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    Alumno newAlumno = new Alumno(size+1, nombre, carrera,noControl,celular,correo);
                    base.addAlumno(newAlumno);
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}