package com.example.manus;

import android.content.Context;
import android.graphics.Paint;
import android.widget.Toast;

public class Utils {

    public static void toast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static long getTimestamp(){
        return System.currentTimeMillis();
    }
}
