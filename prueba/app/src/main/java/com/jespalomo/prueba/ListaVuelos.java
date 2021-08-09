package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListaVuelos extends AppCompatActivity {

    List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vuelos);

        init();
    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new ListElement("MAD-LJU", "ESCALAS - 5h 15min", "77€"));
        elements.add(new ListElement("MAD-VIE", "DIRECTO - 2h 30min", "102€"));
        elements.add(new ListElement("BCN-LJU", "DIRECTO - 1h 55min", "113€"));

        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}