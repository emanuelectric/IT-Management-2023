package com.example.itmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itmanagement.R;
import com.example.itmanagement.activities.EditarDivisaActivity;
import com.example.itmanagement.modelo.Divisa;

import java.util.List;

public class DivisaAdapter extends ArrayAdapter<Divisa> {

    public DivisaAdapter(Context context, List<Divisa> divisas) {
        super(context, 0, divisas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Divisa divisa = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_divisa_layout, parent, false);
        }

        // Configura las vistas con la información de la divisa
        ImageView imageViewAvatar = convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewNombreDivisa = convertView.findViewById(R.id.textViewNombreDivisa);
        TextView textViewSimboloDivisa = convertView.findViewById(R.id.textViewSimboloDivisa);

        // Asigna los valores de la divisa a las vistas
        textViewNombreDivisa.setText(divisa.getNombreDivisa());
        textViewSimboloDivisa.setText(divisa.getSimboloDivisa());

        return convertView;
    }

    // Editar
    public void editarDivisa(Divisa divisa) {
        // Crea un Intent para iniciar EditarDivisaActivity
        Intent intent = new Intent(getContext(), EditarDivisaActivity.class);

        // Puedes pasar información adicional a la actividad, como el ID de la divisa
        intent.putExtra("idDivisa", divisa.getIdDivisa());

        // Inicia la actividad
        getContext().startActivity(intent);
    }


    // Eliminar
    public void eliminarDivisa(Divisa divisa) {

    }

}
