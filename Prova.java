package com.example.simone.mylap2;

/**
 * Created by Simone on 08/11/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Prova extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */

    //  private DbAdapter dbHelper;
    private Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prova);
        final TextView text_cerca = (TextView) findViewById(R.id.SioNo);


        Button cerca = (Button) findViewById(R.id.cerca);
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText emailC = (EditText) findViewById(R.id.editTextaa);

                final String email = emailC.getText().toString();
                DbAdapter dbHelper = new DbAdapter(getApplicationContext());
                dbHelper.open();
                cursor = dbHelper.tuttiGliUtenti();
                int trovato=0;
                String emailCursore;
                while (cursor.moveToNext()) {

                    if(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NOME))!=null)
                      emailCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NOME));
                    else
                      emailCursore="null";

                    if (emailCursore.equals(email)) {

                        trovato=1;


                    }



                }
                if (trovato==1)
                text_cerca.setText("Trovato");
                else
                text_cerca.setText("Non trovato");

               dbHelper.close();
                cursor.close();
            }
        });

    }
}

