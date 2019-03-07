package mx.edu.ittepic.cursos;


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

        RecyclerView personaView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        personaView.setLayoutManager(linearLayoutManager);
        personaView.setHasFixedSize(true);

        base = new BD(this);
        List<Persona> personas = base.personas();
        size = personas.size();

        if(personas.size() > 0){
            personaView.setVisibility(View.VISIBLE);
            PersonaAdapter adapter = new PersonaAdapter(this, personas);
            personaView.setAdapter(adapter);

        }else {
            personaView.setVisibility(View.GONE);
            Toast.makeText(this, "No existen Personas", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton agregar = findViewById(R.id.agregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarPersona();
            }
        });
    }

    private void agregarPersona(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_persona_layout, null);

        final EditText claveC = subView.findViewById(R.id.clave);
        final EditText nombreC = subView.findViewById(R.id.nombre);
        final EditText salarioC = subView.findViewById(R.id.salario);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Persona");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String clave = claveC.getText().toString();
                final String nombre = nombreC.getText().toString();
                final String salario = salarioC.getText().toString();

                if(TextUtils.isEmpty(clave) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(salario)){
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    Persona newPersona = new Persona(size+1, clave, nombre,salario);
                    base.addPersona(newPersona);
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