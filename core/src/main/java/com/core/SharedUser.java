package com.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.models.User;

public class SharedUser {
    private static String TAG = "desa ::";

    public static SharedPreferences getInstance(Context context) {
        Log.d(TAG, "memulai persistansi");
        return context.getSharedPreferences(context.getResources().getString(R.string.KEY_SHARED_DATA), 0);
    }

    public static void setUser(Context context, User user) {
        try {
            SharedPreferences.Editor editor = getInstance(context).edit();
            Gson gson = new Gson();
            editor.putString(context.getResources().getString(R.string.KEY_DATA_USER), gson.toJson(user));

            editor.apply();
            if (user == null) {
                Log.d(TAG, "mendengarkan user ");
            } else {
                Log.d(TAG, "user tidak ada");
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage().toString());
        }
    }


    public static User getUser(Context context) {
        try {
            Gson gson = new Gson();
            String json = getInstance(context).getString(context.getResources().getString(R.string.KEY_DATA_USER), "");
            User user = gson.fromJson(json, User.class);
            Log.d(TAG, "mengambil user");
            return user;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage().toString());
            return null;
        }
    }


    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.remove(context.getResources().getString(R.string.KEY_DATA_USER));
        editor.apply();
        Log.d(TAG, "menghapus persistensi");
    }
}
