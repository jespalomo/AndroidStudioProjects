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
    ListElement vuelo=new ListElement();

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
            vuelo = (ListElement) m.getTag();
            ((TextView) v.findViewById(R.id.info_window_tercera)).setText(mascarillas[vuelo.getP().getAlerta()]);
            ((TextView) v.findViewById(R.id.info_window_segunda)).setText(riesgo[vuelo.getP().getAlerta()]);
            ((TextView) v.findViewById(R.id.info_window_segunda)).setTextColor(colores[vuelo.getP().getAlerta()]);
            ((TextView) v.findViewById(R.id.info_window_primera)).setText(vuelo.getP().getNombre());
            ((ImageView) v.findViewById(R.id.info_window_imagen)).setColorFilter(coloresmasc[vuelo.getP().getAlerta()]);
        }
        return v;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }


}