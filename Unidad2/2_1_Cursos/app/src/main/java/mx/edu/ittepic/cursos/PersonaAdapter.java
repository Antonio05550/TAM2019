package mx.edu.ittepic.cursos;

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

public class PersonaAdapter extends RecyclerView.Adapter<PersonaViewHolder>{
    private Context context;
    private List<Persona> personas;
    private BD bd;

    public PersonaAdapter(Context context, List<Persona> personas) {
        this.context = context;
        this.personas = personas;
        bd = new BD(context);
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_layout, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonaViewHolder holder, int position) {
        final Persona persona = personas.get(position);

        holder.clave.setText(persona.getClave());
        holder.nombre.setText(persona.getNombre());
        holder.salario.setText(persona.getSalario());

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarPersona(persona);
            }
        });

        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bd.deletePersona(persona.getId());

                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }


    private void editarPersona(final Persona persona){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_persona_layout, null);

        final EditText claveC = subView.findViewById(R.id.clave);
        final EditText nombreC = subView.findViewById(R.id.nombre);
        final EditText salarioC = subView.findViewById(R.id.salario);

        if(persona != null){
            claveC.setText(persona.getClave());
            nombreC.setText(persona.getNombre());
            salarioC.setText(persona.getSalario());

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar Persona");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDITAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String clave = claveC.getText().toString();
                final String nombre = nombreC.getText().toString();
                final String salario = salarioC.getText().toString();

                if(TextUtils.isEmpty(clave) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(salario)){
                    Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }
                else{
                    bd.updatePersona(new Persona(persona.getId(), clave, nombre,salario));
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
