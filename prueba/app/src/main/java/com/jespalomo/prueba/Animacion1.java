package com.jespalomo.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Animacion1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animacion1);

        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView titulo = (TextView)findViewById(R.id.tituloApp);
        ImageView logo = (ImageView) findViewById(R.id.imagenLogo);

        titulo.setAnimation(anim2);
        logo.setAnimation(anim1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent next = new Intent(Animacion1.this, Seleccion.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(logo, "logoTrans");
                pairs[1] = new Pair<View, String>(titulo, "textTrans");

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Animacion1.this, pairs);
                    startActivity(next, options.toBundle());
                }else{
                    startActivity(next);
                    finish();
                }


            }
        }, 4000);
    }
}