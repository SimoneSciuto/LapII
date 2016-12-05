package com.example.simone.mylap2;

/**
 * Created by Simone on 11/10/2016.
 */
import android.app.ActionBar;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;
import  android.content.*;


public class Form extends AppCompatActivity {
    /** Called when the activity is first created. */

  //  private DbAdapter dbHelper;
    private Cursor cursor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);


        Button back = (Button) findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backMenu = new Intent(Form.this, Menu.class);
                startActivity(backMenu);
            }
        });


        final TextView text_name = (TextView) findViewById(R.id.view_nome);
        final TextView text_lastname = (TextView) findViewById(R.id.view_cognome);
        final TextView text_email = (TextView) findViewById(R.id.view_email);
        final TextView text_altezza = (TextView) findViewById(R.id.view_altezza);
        final TextView text_capelli = (TextView) findViewById(R.id.view_capelli);
        final TextView text_sesso = (TextView) findViewById(R.id.view_sesso);


        SharedPreferences settings = getSharedPreferences("PROVA", Context.MODE_PRIVATE);
        String email = settings.getString("email", null);

        DbAdapter dbHelper = new DbAdapter(getApplicationContext());
        dbHelper.open();
        cursor = dbHelper.tuttiGliUtenti();
        while (cursor.moveToNext()) {

            String emailCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_EMAIL));

            if(emailCursore.equals(email))
            {
                String nomeCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NOME));
                String cognomeCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_COGNOME));
                String altezzaCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_ALTEZZA));
                String capelliCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_CAPELLI));
                String sessoCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_SESSO));

                text_name.setText(nomeCursore);
                text_lastname.setText(cognomeCursore);
                text_email.setText(emailCursore);
                text_altezza.setText(altezzaCursore);
                text_capelli.setText(capelliCursore);
                text_sesso.setText(sessoCursore);

            }

        }
        cursor.close();
        dbHelper.close();
    }
 }

