package mx.edu.ittepic.laboratorio2;


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

        RecyclerView multaView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        multaView.setLayoutManager(linearLayoutManager);
        multaView.setHasFixedSize(true);

        base = new BD(this);
        List<Multa> multas = base.multas();
        size = multas.size();

        if(multas.size() > 0){
            multaView.setVisibility(View.VISIBLE);
            MultaAdapter adapter = new MultaAdapter(this, multas );
            multaView.setAdapter(adapter);

        }else {
            multaView.setVisibility(View.GONE);
            Toast.makeText(this, "No existen Multas", Toast.LENGTH_LONG).show();
        }

        FloatingActionButton agregar = findViewById(R.id.agregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarMulta();
            }
        });
    }

    private void agregarMulta(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_multa_layout, null);

        final EditText numeroC = subView.findViewById(R.id.numero );
        final EditText celularC = subView.findViewById(R.id.celular );
        final EditText correoC = subView.findViewById(R.id.correo);
        final EditText montoC = subView.findViewById(R.id.monto );
        final EditText puntosC = subView.findViewById(R.id.puntos);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Multa");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int numero = Integer.parseInt(numeroC.getText().toString()) ;
                final String celular = celularC.getText().toString();
                final String correo = correoC.getText().toString();
                final int monto = Integer.parseInt(montoC.getText().toString());
                final int puntos = Integer.parseInt(puntosC.getText().toString());

                if(numero<=0 || TextUtils.isEmpty(celular) || TextUtils.isEmpty(correo)
                        || monto<=0 || puntos<=0){
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    Multa newMulta = new Multa(size+1, numero, celular,correo,monto,puntos);
                    base.addMulta( newMulta );
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