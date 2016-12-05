package com.example.simone.mylap2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by Simone on 24/10/2016.
 */
public class Share extends AppCompatActivity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    private Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        SharedPreferences settings = getSharedPreferences("PROVA", Context.MODE_PRIVATE);
        String email = settings.getString("email", null);
        String nome=new String();
        String punti=new String();

        DbAdapter dbHelper = new DbAdapter(getApplicationContext());
        dbHelper.open();
        cursor = dbHelper.tuttiGliUtenti();
        while (cursor.moveToNext()) {

            String emailCursore = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_EMAIL));

            if (emailCursore.equals(email)) {
                nome = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NOME));
                punti = cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_PUNTI));
            }
        }

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Mi chiamo " +nome+ " e questo è il mio profilo!")
                    .setContentDescription(
                            "Guarda il profilo che ho creato con MyLap2!")
                    .setContentUrl(Uri.parse("https://it.wikipedia.org/wiki/Prova"))
                    .build();

            shareDialog.show(linkContent);
        //    AppEventsLogger logger = AppEventsLogger.newLogger(this);
          //  logger.logEvent(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT});
        }
        setContentView(R.layout.share);
        TextView saluto = (TextView) findViewById(R.id.saluto);
        TextView points = (TextView) findViewById(R.id.punti);

        saluto.setText("Ciao "+nome);
        points.setText("Punti acquisiti = "+punti);

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }
}
