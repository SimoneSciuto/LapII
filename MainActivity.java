package com.example.simone.mylap2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.content.*;

public class MainActivity extends AppCompatActivity {

  //  private DbAdapter dbHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        final DbAdapter dbHelper = new DbAdapter(getApplicationContext());

        // Premo su login..
        Button login = (Button) findViewById(R.id.login);
                login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final EditText emailView = (EditText) findViewById(R.id.emailReg);
                final EditText pswView = (EditText) findViewById(R.id.pswReg);
                String email = emailView.getText().toString();
                String psw = pswView.getText().toString();
                int emailEsatta=0;
                int pswEsatta=0;

                //ricerca tra gli utenti registrati..
                dbHelper.open();
                cursor = dbHelper.tuttiGliUtenti();
                Bundle bundle = new Bundle();
                while (cursor.moveToNext()) {

                    String emailCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_EMAIL));
                    String pswCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_PSW));
                    if(emailCursore.equals(email))
                       emailEsatta = 1;

                    if(pswCursore.equals(psw))
                       pswEsatta=1;
                }
                //entro..
                if((emailEsatta==1) && (pswEsatta==1)&&(email.equals("")!=true))
                {
                    SharedPreferences settings = getSharedPreferences("PROVA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("email", email);
                    editor.putString("psw", psw);
                    editor.commit();

                    Intent openMenu = new Intent(MainActivity.this, com.example.simone.mylap2.Menu.class);
                    openMenu.putExtras(bundle);

                    // passo all'attivazione dell'activity Menu.java
                    startActivity(openMenu);
                    Toast mioToast = Toast.makeText(MainActivity.this, "Benvenuto !", Toast.LENGTH_SHORT);
                    mioToast.show();
                }

                //psw errata..
                if(emailEsatta==1 && (pswEsatta==0)&&(email.equals("")!=true))
                {
                   Toast mioToast = Toast.makeText(MainActivity.this, "Password errata !", Toast.LENGTH_SHORT);
                    mioToast.show();
                }

                //non registrato..
                if(emailEsatta==0)
                {
                    Toast mioToast = Toast.makeText(MainActivity.this, "Non sei registrato !", Toast.LENGTH_SHORT);
                    mioToast.show();
                }
                if((email.equals("")==true))
                {
                    Toast mioToast = Toast.makeText(MainActivity.this, "Registrati !", Toast.LENGTH_SHORT);
                    mioToast.show();
                }

                cursor.close();
                dbHelper.close();


            }
        });

        // Premo su Registrati..
        final Button registrazione = (Button) findViewById(R.id.reg);
        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final EditText emailView1 = (EditText) findViewById(R.id.emailReg);
                final EditText pswView1 = (EditText) findViewById(R.id.pswReg);
                String email = emailView1.getText().toString();
                String psw = pswView1.getText().toString();
                int emailEsatta=0;

                //ricerca tra gli utenti registrati..
                dbHelper.open();
                cursor = dbHelper.tuttiGliUtenti();
                while (cursor.moveToNext())
                {
                    String emailCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_EMAIL));
                    if(emailCursore.equals(email))
                        emailEsatta = 1;
                }
            //    cursor.close();
            //    dbHelper.close();

                if((emailEsatta==1)&&(email.equals("")!=true))
                {
                   Toast mioToast = Toast.makeText(MainActivity.this, "Sei già registrato !", Toast.LENGTH_SHORT);
                    mioToast.show();
                }

                if(email.equals(""))
                {
                    Toast mioToast = Toast.makeText(MainActivity.this, "Inserisci l'email !", Toast.LENGTH_SHORT);
                    mioToast.show();
                }

                Bundle bundle = new Bundle();
                if((emailEsatta==0) && (psw.equals("")!=true))
                {
                    //registrazione
                    dbHelper.open();
                    dbHelper.createContact(emailView1.getText().toString(), pswView1.getText().toString(), null, null, null, null, null, "0");
                  //  dbHelper.close();

                    Toast mioToast = Toast.makeText(MainActivity.this, "Registrazione completata !", Toast.LENGTH_SHORT);
                    mioToast.show();

                    Intent openReg = new Intent(MainActivity.this, com.example.simone.mylap2.Registrazione.class);
                    // passo all'attivazione dell'activity Registrazione.java
                    startActivity(openReg);
/*
                    Intent openReg = new Intent(MainActivity.this, com.example.simone.mylap2.Registrazione.class);
                    openReg.putExtras(bundle);
                    // passo all'attivazione dell'activity Registrazione.java
                      startActivity(openReg);
                      */

                }
                    dbHelper.close();
                    cursor.close();

            }
        });

    }
}

