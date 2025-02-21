package com.senati.mangaka.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiPirateManga implements ApiManga {
    private static final String BASE_URL = "https://www.animeallstar30.com/";
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
        String url = BASE_URL + "feeds/posts/default/-/Nuevo?max-results=60&orderby=published&alt=json";

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

    public void getCaps(final VolleyCallback callback) {
        String url = BASE_URL + "feeds/posts/default/-/Nuevo?max-results=60&orderby=published&alt=json";

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

    public void getCap(final VolleyCallback callback) {
        String url = BASE_URL + "feeds/posts/default/-/Nuevo?max-results=60&orderby=published&alt=json";

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

    @Override
    public void getCaps(final String id, final VolleyCallback callback) {
        throw new UnsupportedOperationException("getCaps() con  ID no está soportado en ApiMangaDex.");
    }

    @Override
    public void getCap(final String id, final VolleyCallback callback) {
        throw new UnsupportedOperationException("getCap() con capId no está soportado en ApiMangaDex.");
    }
}