package com.example.simone.mylap2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.*;

/**
 * Created by Simone on 17/10/2016.
 */
public class InserimentoDati extends Activity {

    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ins_dati);

      //  final Bundle bundle = this.getIntent().getExtras();
      //  DbAdapter dbHelper = new DbAdapter(getApplicationContext());
      //  dbHelper.open();



        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backMenu = new Intent(InserimentoDati.this, Menu.class);
                startActivity(backMenu);
              }
        });


        final Button insDati = (Button) findViewById(R.id.insDati);
        insDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch (v.getId()) {
                    case R.id.insDati:

                        final EditText edit_name = (EditText) findViewById(R.id.nome);
                        final EditText edit_lastname = (EditText) findViewById(R.id.cognome);
                        final EditText edit_altezza = (EditText) findViewById(R.id.altezzaCM);
                        final Spinner edit_capelliSP = (Spinner) findViewById(R.id.capelli);

                        String sesso = new String();
                        RadioGroup radio = (RadioGroup) findViewById(R.id.sesso);
                        RadioButton rb = (RadioButton) findViewById(radio.getCheckedRadioButtonId());
                        sesso = rb.getText().toString();

                        SharedPreferences settings = getSharedPreferences("PROVA", Context.MODE_PRIVATE);
                        String email = settings.getString("email", null);
                        String psw = settings.getString("psw", null);
                        String nome = edit_name.getText().toString();
                        String cognome = edit_lastname.getText().toString();
                        String altezza = edit_altezza.getText().toString();
                        String capelli = edit_capelliSP.getSelectedItem().toString();

                        DbAdapter dbHelper = new DbAdapter(getApplicationContext());
                        dbHelper.open();
                        cursor = dbHelper.tuttiGliUtenti();
                        while (cursor.moveToNext()) {

                            String emailCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_EMAIL));

                            if(emailCursore.equals(email))
                            {
                                dbHelper.open();
                                String punti = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_PUNTI));
                                dbHelper.updateContact(email, psw, nome, cognome, altezza, capelli, sesso, punti);
                            }

                        }
                        cursor.close();
                        dbHelper.close();


/*
                        SharedPreferences settings = getSharedPreferences("PROVA", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("nome", edit_name.getText().toString());
                        editor.putString("cognome", edit_lastname.getText().toString());
                        editor.putString("altezza", edit_altezza.getText().toString());
                        editor.putString("capelli", edit_capelliSP.getSelectedItem().toString());
                        editor.putString("sesso", sesso);
                        editor.commit();

*/
                        Intent form_intent = new Intent(getApplicationContext(), Form.class);
                        startActivity(form_intent);
                        break;
                }
            }

        });
    }
}

