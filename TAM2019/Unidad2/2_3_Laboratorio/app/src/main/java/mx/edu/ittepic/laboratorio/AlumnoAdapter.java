package mx.edu.ittepic.laboratorio;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoViewHolder>{
    private Context context;
    private List<Alumno> alumnos;
    private BD bd;


    public AlumnoAdapter(Context context, List<Alumno> alumnos) {
        this.context = context;
        this.alumnos = alumnos;
        bd = new BD(context);
    }

    @Override
    public AlumnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_layout, parent, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlumnoViewHolder holder, int position) {
        final Alumno alumno = alumnos.get(position);

        holder.nombre.setText(alumno.getNombre());
        holder.carrera.setText(alumno.getCarrera());
        holder.noControl.setText(alumno.getNoControl());
        holder.celular.setText(alumno.getCelular());
        holder.correo.setText(alumno.getCorreo());

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarAlumno(alumno);
            }
        });

        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bd.deleteAlumno(alumno.getId());

                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarAlumno(alumno);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }


    private void editarAlumno(final Alumno alumno){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_alumno_layout, null);

        final EditText nombreC = subView.findViewById(R.id.nombre);
        final EditText carreraC = subView.findViewById(R.id.carrera);
        final EditText noControlC = subView.findViewById(R.id.noControl);
        final EditText celularC = subView.findViewById(R.id.celular);
        final EditText correoC = subView.findViewById(R.id.correo);

        if(alumno != null){
            nombreC.setText(alumno.getNombre());
            carreraC.setText(alumno.getCarrera());
            noControlC.setText(alumno.getNoControl());
            celularC.setText(alumno.getCelular());
            correoC.setText(alumno.getCorreo());

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar Alumno");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String nombre = nombreC.getText().toString();
                final String carrera = carreraC.getText().toString();
                final String noControl = noControlC.getText().toString();
                final String celular = celularC.getText().toString();
                final String correo = correoC.getText().toString();

                if(TextUtils.isEmpty(nombre) || TextUtils.isEmpty(carrera) || TextUtils.isEmpty(noControl)
                        || TextUtils.isEmpty(celular) || TextUtils.isEmpty(correo)){
                    Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    bd.updateAlumno(new Alumno(alumno.getId(), nombre, carrera,noControl,celular,correo));
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Cancelado", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    public void mostrarAlumno(final Alumno alumno){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_alumno_layout, null);

        final EditText nombreC = subView.findViewById(R.id.nombre);
        final EditText carreraC = subView.findViewById(R.id.carrera);
        final EditText noControlC = subView.findViewById(R.id.noControl);
        final EditText celularC = subView.findViewById(R.id.celular);
        final EditText correoC = subView.findViewById(R.id.correo);
        nombreC.setEnabled( false );
        carreraC.setEnabled( false );
        noControlC.setEnabled( false );
        celularC.setEnabled( false );
        correoC.setEnabled( false );


        if(alumno != null){
            nombreC.setText(alumno.getNombre());
            carreraC.setText(alumno.getCarrera());
            noControlC.setText(alumno.getNoControl());
            celularC.setText(alumno.getCelular());
            correoC.setText(alumno.getCorreo());

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Detalles Alumno");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

}
