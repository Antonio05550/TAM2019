package mx.edu.ittepic.laboratorio;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AlumnoViewHolder extends RecyclerView.ViewHolder {

    public ImageView icono;
    public TextView nombre, carrera, noControl,celular,correo;
    public Button editar, borrar;

    public  AlumnoViewHolder(View itemView){
        super(itemView);
        nombre = itemView.findViewById(R.id.nombre);
        carrera = itemView.findViewById(R.id.carrera);
        noControl = itemView.findViewById(R.id.noControl);
        celular = itemView.findViewById(R.id.celular);
        correo = itemView.findViewById(R.id.correo);
        editar = itemView.findViewById(R.id.editar);
        borrar = itemView.findViewById(R.id.borrar);
    }
}
