package mx.edu.ittepic.e2__joseantoniorivasnavarrete;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorAlumno extends RecyclerView.Adapter<AdaptadorAlumno.ExampleViewHolder> {
    private ArrayList<ItemAlumno> alumnos;
    private OnItemClickListener oyente;

    public interface OnItemClickListener {
        void onItemContact(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener oyente) {
        this.oyente = oyente;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView foto;
        public TextView nombre;
        public TextView carrera;
        public TextView noControl;
        public ImageView eliminar;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
            nombre = itemView.findViewById(R.id.nombre);
            carrera = itemView.findViewById(R.id.carrera);
            noControl = itemView.findViewById(R.id.noControl);
            eliminar = itemView.findViewById(R.id.eliminar);

            foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemContact(position);
                        }
                    }
                }
            });

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public AdaptadorAlumno(ArrayList<ItemAlumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, oyente);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ItemAlumno currentItem = alumnos.get(position);

        holder.foto.setImageResource(currentItem.getImage());
        holder.nombre.setText(currentItem.getNombre());
        holder.carrera.setText(currentItem.getCarrera());
        holder.noControl.setText(currentItem.getNoControl());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }
}