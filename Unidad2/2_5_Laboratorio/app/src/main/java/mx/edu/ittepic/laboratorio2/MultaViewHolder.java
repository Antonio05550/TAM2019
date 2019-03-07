package mx.edu.ittepic.laboratorio2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MultaViewHolder extends RecyclerView.ViewHolder {

    public TextView numero, celular, correo,monto,puntos;
    public ImageView editar, info;

    public MultaViewHolder(View itemView){
        super(itemView);
        numero = itemView.findViewById(R.id.numero );
        celular = itemView.findViewById(R.id.celular );
        correo = itemView.findViewById(R.id.correo);
        editar = itemView.findViewById(R.id.editar);
        info = itemView.findViewById(R.id.info);
    }
}
