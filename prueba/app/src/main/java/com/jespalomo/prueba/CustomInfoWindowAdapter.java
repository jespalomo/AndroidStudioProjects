package com.jespalomo.prueba;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;
    Pais pais=new Pais();

    private static final int coloresmasc []= {0xff3be155,0xff3be155,0xfff50505};
    private static final int colores []= {0xff3be155,0xfff5a905,0xfff50505};
    private String riesgo[]= {"Pais con riesgo bajo",
    "Pais con riesgo intermedio",
    "Pais con alto riesgo"};
    private String mascarillas[]= {"Mascarilla no obligatoria al aire libre",
            "Mascarilla no obligatoria al aire libre",
            "Mascarilla obligatoria al aire libre"};

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
    }


    @Override
    public View getInfoContents(final Marker m) {
        //Carga layout personalizado.
        View v = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.ventana_info_mapa, null);
        if(m!=null) {
            pais = (Pais) m.getTag();
            ((TextView) v.findViewById(R.id.info_window_tercera)).setText(mascarillas[pais.getAlerta()]);
            ((TextView) v.findViewById(R.id.info_window_segunda)).setText(riesgo[pais.getAlerta()]);
            ((TextView) v.findViewById(R.id.info_window_segunda)).setTextColor(colores[pais.getAlerta()]);
            ((TextView) v.findViewById(R.id.info_window_primera)).setText(pais.getNombre());
            ((ImageView) v.findViewById(R.id.info_window_imagen)).setColorFilter(coloresmasc[pais.getAlerta()]);
        }
        return v;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }


}