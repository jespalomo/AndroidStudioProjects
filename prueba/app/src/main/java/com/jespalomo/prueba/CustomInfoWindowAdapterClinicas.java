package com.jespalomo.prueba;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapterClinicas implements GoogleMap.InfoWindowAdapter {
    Context context;
    Clinica cli=new Clinica();

    private static final int colores []= {0xff3be155,0xff3be155,0xfff50505};
    private String riesgo[]= {"Pais con riesgo bajo",
            "Pais con riesgo intermedio",
            "Pais con alto riesgo"};
    private String mascarillas[]= {"Mascarilla no obligatoria al aire libre",
            "Mascarilla no obligatoria al aire libre",
            "Mascarilla obligatoria al aire libre"};

    public CustomInfoWindowAdapterClinicas(Context context) {
        this.context = context;
    }


    @Override
    public View getInfoContents(final Marker m) {
        //Carga layout personalizado.
        View v = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.ventana_info_mapa, null);
        if(m!=null) {
            cli = (Clinica) m.getTag();
            ((TextView) v.findViewById(R.id.info_window_tercera)).setText(cli.getTelefono());
            ((TextView) v.findViewById(R.id.info_window_segunda)).setText(cli.getDireccion());
            ((TextView) v.findViewById(R.id.info_window_primera)).setText(cli.getNombre());
            ((ImageView) v.findViewById(R.id.info_window_imagen)).setImageResource(R.drawable.clinica_icono);
            ((ImageView) v.findViewById(R.id.info_window_imagen)).setColorFilter(colores[2]);

        }
        return v;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }

}
