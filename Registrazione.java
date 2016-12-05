package com.example.simone.mylap2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.*;

/**
 * Created by Simone on 31/10/2016.
 */
public class Registrazione extends AppCompatActivity
{
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);
        Button nessuno = (Button) findViewById(R.id.nessuno);
        Button premio = (Button) findViewById(R.id.premio);

   //     final DbAdapter dbHelper = new DbAdapter(getApplicationContext());

        premio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final EditText nomeView = (EditText) findViewById(R.id.nomeA);
                final EditText cognomeView = (EditText) findViewById(R.id.cognomeA);
                String nome = nomeView.getText().toString();
                String cognome = cognomeView.getText().toString();
                int nomeEsatto=0;
                int cognomeEsatto=0;
                int aggiornato=0;

                DbAdapter dbHelper = new DbAdapter(getApplicationContext());
                dbHelper.open();
                cursor = dbHelper.tuttiGliUtenti();
                while ((cursor.moveToNext()) && (aggiornato==0)) {

                    String nomeCursore;
                    String cognomeCursore;


                    if(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NOME))!=null)
                      nomeCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NOME));
                    else
                      nomeCursore="NonPresente";

                    if(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_COGNOME))!=null)
                      cognomeCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_COGNOME));
                    else
                      cognomeCursore="NonPresente";

                    if(nomeCursore.equals(nome))
                        nomeEsatto = 1;

                    if(cognomeCursore.equals(cognome))
                        cognomeEsatto = 1;
                    if(nomeEsatto==1 && cognomeEsatto==1)
                    {
                        String nome1 = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NOME));
                        String cognome1 = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_COGNOME));
                        String psw = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_PSW));
                        String altezza = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ALTEZZA));
                        String capelli = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_CAPELLI));
                        String sesso = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_SESSO));
                        String email = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_EMAIL));
                        String punti = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_PUNTI));


                        switch (punti){
                            case "0":
                                punti="100";
                                break;

                            case "100":
                                punti="200";
                                break;

                            case "200":
                                punti="300";
                                break;

                            case "300":
                                punti="400";
                                break;

                            case "400":
                                punti="500";
                                break;

                            case "500":
                                punti="600";
                                break;

                            case "600":
                                punti="700";
                                break;

                            case "700":
                                punti="800";
                                break;

                            case "800":
                                punti="900";
                                break;

                            case "900":
                                punti="1000";
                                break;

                            }
                        dbHelper.open();
                        dbHelper.updateContact(email, psw, nome1, cognome1, altezza, capelli, sesso, punti);
                        aggiornato=1;

                        }

                }
                cursor.close();
                dbHelper.close();


                if((nomeEsatto==1 && cognomeEsatto==1)==true)
                {
                    Intent backHome = new Intent(Registrazione.this, com.example.simone.mylap2.MainActivity.class);
                    Toast mioToast = Toast.makeText(Registrazione.this, "Amico premiato !", Toast.LENGTH_SHORT);
                    mioToast.show();
                    // passo all'attivazione dell'activity Registrazione.java
                    startActivity(backHome);
                }
                if((nomeEsatto==1 && cognomeEsatto==1)!=true)
                {
                    Intent backHome = new Intent(Registrazione.this, com.example.simone.mylap2.MainActivity.class);
                    Toast mioToast = Toast.makeText(Registrazione.this, "Amico non trovato !", Toast.LENGTH_SHORT);
                    mioToast.show();
                    // passo all'attivazione dell'activity Registrazione.java
                    startActivity(backHome);
                }


            }
        });


        nessuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent backHome = new Intent(Registrazione.this, com.example.simone.mylap2.MainActivity.class);
                Toast mioToast = Toast.makeText(Registrazione.this, "Non hai premiato nessuno !", Toast.LENGTH_SHORT);
                mioToast.show();
                // passo all'attivazione dell'activity Registrazione.java
                startActivity(backHome);
            }
        });

    }
}
