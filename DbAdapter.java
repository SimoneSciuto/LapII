package com.example.simone.mylap2;

/**
 * Created by Simone on 27/10/2016.
 */
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class DbAdapter {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapter.class.getSimpleName();

     Context context;
     SQLiteDatabase database;
     DatabaseHelper dbHelper;

    // Database fields
    private static final String DATABASE_TABLE      = "utentiapp";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PSW = "password";
    public static final String KEY_NOME = "nome";
    public static final String KEY_COGNOME = "cognome";
    public static final String KEY_ALTEZZA = "altezza";
    public static final String KEY_CAPELLI = "capelli";
    public static final String KEY_SESSO = "sesso";
    public static final String KEY_PUNTI = "punti";

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String email, String password, String nome, String cognome, String altezza, String capelli, String sesso, String punti ) {
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email);
        values.put(KEY_PSW, password);
        values.put(KEY_NOME, nome);
        values.put(KEY_COGNOME, cognome);
        values.put(KEY_ALTEZZA, altezza);
        values.put(KEY_CAPELLI, capelli);
        values.put(KEY_SESSO, sesso);
        values.put(KEY_PUNTI, punti);
        return values;
    }

      //create a contact
    public long createContact(String email, String password, String nome, String cognome, String altezza, String capelli, String sesso, String punti) {
        ContentValues initialValues = createContentValues(email, password, nome, cognome, altezza, capelli, sesso, punti);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a contact

        public boolean updateContact(String email, String password, String nome, String cognome, String altezza, String capelli, String sesso, String punti ) {
        ContentValues updateValues = createContentValues(email, password, nome, cognome, altezza, capelli, sesso, punti);
        return database.update(DATABASE_TABLE, updateValues,  "email = ? ", new String[] { email }) > 0;
    }


    //delete a contact
    public boolean deleteContact(String email) {
        return database.delete(DATABASE_TABLE, KEY_EMAIL + "=" + email, null) > 0;
    }

    //fetch all contacts
    public Cursor tuttiGliUtenti() {
        return database.query(DATABASE_TABLE, new String[] { KEY_EMAIL, KEY_PSW, KEY_NOME, KEY_COGNOME, KEY_ALTEZZA, KEY_CAPELLI, KEY_SESSO, KEY_PUNTI}, null, null, null, null, null);
    }
/*
    public Cursor getNome( String cognome ) throws SQLException {

        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_NAME},
                KEY_SURNAME + "=" + cognome, null, null, null, null, null);

        return mCursor;
    }


    //fetch contacts filter by a string
    public Cursor fetchContactsByFilter(String filter) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_NAME, KEY_SURNAME, KEY_AGE },
                KEY_NAME + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }
    */
}