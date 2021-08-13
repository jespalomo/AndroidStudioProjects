package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Seleccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();

        if(id == R.id.home){
            Intent next = new Intent(this, MainActivity.class);
            startActivity(next);
            return true;
        }else if(id == R.id.clinicas){
            Intent next = new Intent(this, CompruebaUbicacion.class);
            startActivity(next);
            return true;
        }else if(id == R.id.planearviaje){
            Intent next = new Intent(this, MainActivity.class);
            startActivity(next);
            return true;
        }else if(id == R.id.about){
            Intent next = new Intent(this, SobreMi.class);
            startActivity(next);
            return true;
        }return super.onOptionsItemSelected(item);
    }
    public void botonUbi(View view){
        Intent next = new Intent(this, CompruebaUbicacion.class);
        startActivity(next);
    }
    public void botonMain(View view){
        Intent next = new Intent(this, MainActivity.class);
        startActivity(next);
    }
}