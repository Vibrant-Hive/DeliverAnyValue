package com.deliver.any.value.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.deliver.any.value.constants.Constants;

public class CommonUtilities {

    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;
    private static Context mContext;

    private static void createSharedPreferenceEditor(Context context) {
        try {
            mContext = context;
            sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void putPrefString(Context context, String key, String value) {
        try {
            createSharedPreferenceEditor(context);
            editor.putString(key, value);
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static String getPrefString(String key) {
        String value = null;
        try {
            value = sharedPreferences.getString(key, key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }
}

