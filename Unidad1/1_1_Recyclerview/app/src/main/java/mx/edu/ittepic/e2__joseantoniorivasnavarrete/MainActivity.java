package mx.edu.ittepic.e2__joseantoniorivasnavarrete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ItemAlumno> alumnos;

    private RecyclerView recyclerView;
    private AdaptadorAlumno adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
    }

    public void createExampleList() {
        alumnos = new ArrayList<>();
        alumnos.add( new ItemAlumno(R.drawable.contact, "José Antonio RIvas Navarrete","15400802","TICs"));
        alumnos.add( new ItemAlumno(R.drawable.contact, "Alondra Magdalena Gonzalez Cruz","15400000","TICs"));
        alumnos.add( new ItemAlumno(R.drawable.contact, "Francisco Javier Baltazar Ramirez","15400802","TICs"));
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdaptadorAlumno(alumnos);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdaptadorAlumno.OnItemClickListener() {
            @Override
            public void onItemContact(int position) {
                Toast.makeText( getApplicationContext(), "Modificar: "+alumnos.get( position ).getNombre(), Toast.LENGTH_SHORT ).show();
            }
            @Override
            public void onDeleteClick(int position) {
                Toast.makeText( getApplicationContext(), "Eliminar: "+alumnos.get( position ).getNombre(), Toast.LENGTH_SHORT ).show();
            }
        });
    }
}