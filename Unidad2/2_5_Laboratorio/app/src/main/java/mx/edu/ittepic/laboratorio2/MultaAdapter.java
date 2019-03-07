package mx.edu.ittepic.laboratorio2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MultaAdapter extends RecyclerView.Adapter<MultaViewHolder>{
    private Context context;
    private List<Multa> multas;
    private BD bd;

    public MultaAdapter(Context context, List<Multa> multas) {
        this.context = context;
        this.multas = multas;
        bd = new BD(context);
    }

    @Override
    public MultaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multa_layout, parent, false);
        return new MultaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultaViewHolder holder, int position) {
        final Multa multa = multas.get(position);
        holder.numero.setText( String.valueOf(multa.getNumero()));
        holder.celular.setText( multa.getCelular());
        holder.correo.setText( multa.getCorreo());

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarMulta( multa );
            }
        });

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View subView = inflater.inflate(R.layout.det_multa_layout, null);

                final EditText numeroC = subView.findViewById(R.id.numero);
                final EditText montoC = subView.findViewById(R.id.monto );
                final EditText puntosC = subView.findViewById(R.id.puntos);
                numeroC.setEnabled( false );
                montoC.setEnabled( false );
                puntosC.setEnabled( false );

                if(multa != null){
                    numeroC.setText("Matrícula: "+String.valueOf(multa.getNumero()));
                    montoC.setText("Monto total: "+String.valueOf(bd.montoTotal( multa.getNumero())));
                    puntosC.setText("Total de Puntos: "+String.valueOf(bd.puntosTotales( multa.getNumero())));
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Detalles Multa");
                builder.setView(subView);
                builder.create();

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return multas.size();
    }


    private void editarMulta(final Multa multa){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_multa_layout, null);

        final EditText numeroC = subView.findViewById(R.id.numero );
        final EditText celularC = subView.findViewById(R.id.celular );
        final EditText correoC = subView.findViewById(R.id.correo);
        final EditText montoC = subView.findViewById(R.id.monto );
        final EditText puntosC = subView.findViewById(R.id.puntos);

        if(multa != null){
            numeroC.setText( String.valueOf(multa.getNumero()));
            celularC.setText( multa.getCelular());
            correoC.setText( multa.getCorreo());
            montoC.setText( String.valueOf(multa.getMonto()));
            puntosC.setText( String.valueOf(multa.getPuntos()));

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar Multa");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int numero = Integer.parseInt(numeroC.getText().toString());
                final String celular = celularC.getText().toString();
                final String correo = correoC.getText().toString();
                final int monto = Integer.parseInt(montoC.getText().toString());
                final int puntos = Integer.parseInt(puntosC.getText().toString());

                if(numero==0 || TextUtils.isEmpty(celular) || TextUtils.isEmpty(correo)
                        || monto==0 || puntos==0){
                    Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    bd.updateMulta(new Multa( multa.getId(), numero, celular,correo,monto,puntos));
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
}
