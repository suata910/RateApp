package com.sua.tavita.rateapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tavita on 03/06/2015.
 */
public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
