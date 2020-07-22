package com.tdn.data.persistensi;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.tdn.domain.model.UserModel;

public class MyUser {
    private static String TAG = "PERSISTENSI ::";
    private static String NAME = "data_pengaduan";
    private static String
            KEY_KOMENTAR = "komentar",
            KEY_LAPORAN = "laporan",
            KEY_USER = "userdata",
            KEY_CHAT = "chat";
    private static MyUser myUser;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private Context context;

    private MyUser(Context ctx) {
        this.context = ctx;
        sharedPreferences = ctx.getSharedPreferences(NAME, 0);
        editor = sharedPreferences.edit();
    }

    public static MyUser getInstance(Context context) {
        if (myUser == null) {
            myUser = new MyUser(context);
        }
        return myUser;
    }


    public void setUser(UserModel user) {
        try {

            Gson gson = new Gson();
            editor.putString(KEY_USER, gson.toJson(user));
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage().toString());
        }
    }


    public UserModel getUser() {
        try {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(KEY_USER, "");
            UserModel user = gson.fromJson(json, UserModel.class);
            return user;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage().toString());
            return null;
        }
    }

    public String getid(String id, String val) {
        return sharedPreferences.getString(id, null);
    }

    public void setId(String key, String val) {
        editor.putString(key, val);
        editor.apply();
    }

    public void resetId(String key) {
        editor.remove(key);
    }

    public void signOut() {
        editor.remove(KEY_USER);
        editor.apply();
    }
}
