package com.senati.mangaka.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiPirateManga {
    private static final String BASE_URL = "https://api.mangadex.org/";
    private static RequestQueue requestQueue;
    private static ApiPirateManga instance;

    private ApiPirateManga(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized ApiPirateManga getInstance(Context context) {
        if (instance == null) {
            instance = new ApiPirateManga(context);
        }
        return instance;
    }

    public void getMangaList(final VolleyCallback callback) {
        String url = BASE_URL + "manga";

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

    public void getCaps(final String id, final VolleyCallback callback) {
        String url = BASE_URL + "manga/" + id;

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
    public void getCap(final String mangaId, final String capId, final VolleyCallback callback) {
        String url = BASE_URL + "manga/" + mangaId + "/" + capId;

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