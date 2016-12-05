package com.example.simone.mylap2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by Simone on 17/10/2016.
 */
public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Ci troviamo nel menù

        //Se clicco su profilo..
        TextView button1 = (TextView) findViewById(R.id.profilo);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openProfile = new Intent(Menu.this, Form.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openProfile);
            }
        });

        //Se clicco su Nuovo o Modifica..
        TextView button2 = (TextView) findViewById(R.id.new_change);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // definisco l'intenzione
                Intent openPage2 = new Intent(Menu.this, InserimentoDati.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage2);
            }
        });

        TextView share = (TextView) findViewById(R.id.sharedFB);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // definisco l'intenzione
                Intent openShare = new Intent(Menu.this, Share.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openShare);
            }
        });

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // definisco l'intenzione
                Intent backHome = new Intent(Menu.this, MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(backHome);
            }
        });
    }
}