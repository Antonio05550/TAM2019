package mx.edu.ittepic.cursos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonaViewHolder extends RecyclerView.ViewHolder {

    public ImageView editar, borrar;
    public TextView clave, nombre, salario;

    public  PersonaViewHolder(View itemView){
        super(itemView);
        clave = itemView.findViewById(R.id.clave);
        nombre = itemView.findViewById(R.id.nombre);
        salario = itemView.findViewById(R.id.salario);
        editar = itemView.findViewById(R.id.editar);
        borrar = itemView.findViewById(R.id.borrar);
    }
}
