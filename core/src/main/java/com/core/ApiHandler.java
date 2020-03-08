package com.core;

import android.content.Context;
import android.util.Log;

public class ApiHandler {
    private static final String TAG = "desa ::";

    public static boolean cekresponse(int res, Context context, String pesan) {
        try {
            if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_OK))) {
                Log.d(TAG, "response code = 200 : " + pesan);
                return true;
            } else if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_BAD_REQUEST))) {
                Log.d(TAG, "response code = 400 : " + pesan);
                return false;
            } else if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_UNAUTHORIZED))) {
                Log.d(TAG, "response code = 401 : " + pesan);
                return false;
            } else if (res == Integer.parseInt(context.getResources().getString(R.string.HTTP_NOT_FOUND))) {
                Log.d(TAG, "response code = 404 : " + pesan);
                return false;
            } else {
                Log.d(TAG, "response code = Tidak diketahui : " + pesan);
                return false;
            }
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }

    }
}
