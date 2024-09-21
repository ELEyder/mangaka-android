package com.senati.mangaka;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class ApiManga {
    private static final String BASE_URL = "https://9lg836pc-3000.brs.devtunnels.ms/api/";
    private static RequestQueue requestQueue;
    private static ApiManga instance;

    private ApiManga(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized ApiManga getInstance(Context context) {
        if (instance == null) {
            instance = new ApiManga(context);
        }
        return instance;
    }

    public void getMangaList(final VolleyCallback callback) {
        String url = BASE_URL + "mangaList";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                    }
                });

        requestQueue.add(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(String response);
        void onError(VolleyError error);
    }
}