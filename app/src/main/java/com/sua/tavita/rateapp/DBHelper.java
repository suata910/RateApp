package com.sua.tavita.rateapp;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Tavita on 27/06/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String pkg = "com.sua.tavita.rateapp";
    private static String DB_PATH = "/data/data/" + pkg + "/databases/";

    private  static String DB_NAME = "rateappdatabase";

    int[] dbfiles = {R.raw.rateappdatabase};

    private SQLiteDatabase myDataBase;
    private final Context myContext;

     DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
        } else {
            this.getReadableDatabase();
            try {
                CopyDataBase();
            } catch (IOException e) {
                Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                Log.d("Create DB", e.getMessage());
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        } catch (SQLiteException e) {
            Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            Log.d("Check DB", e.getMessage());
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void CopyDataBase() throws IOException {
        InputStream databaseInput = null;
        Resources resources = myContext.getResources();
        String outFileName = DB_PATH + DB_NAME;

        OutputStream databaseOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[512];
        int length;

        for (int i = 0; i < dbfiles.length; i++) {
            databaseInput = resources.openRawResource(dbfiles[i]);
            while ((length = databaseInput.read(buffer)) > 0) {
                databaseOutput.write(buffer, 0, length);
                databaseOutput.flush();
            }
            databaseInput.close();
        }
        databaseOutput.flush();
        databaseOutput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,0);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
