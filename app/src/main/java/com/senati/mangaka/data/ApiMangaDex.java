package com.senati.mangaka.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiMangaDex implements ApiManga {
    private static final String BASE_URL = "https://api.mangadex.org/";
    private static RequestQueue requestQueue;
    private static ApiMangaDex instance;

    private ApiMangaDex(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized ApiMangaDex getInstance(Context context) {
        if (instance == null) {
            instance = new ApiMangaDex(context);
        }
        return instance;
    }

    public void getMangaList(final VolleyCallback callback) {
        String url = BASE_URL + "manga?title=One P&availableTranslatedLanguage[]=es";

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
        String url = BASE_URL + "manga/" + id + "/feed?translatedLanguage[]=es&order[chapter]=desc";

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
    public void getCap(final String capId, final VolleyCallback callback) {
        String url = BASE_URL + "at-home/server/" + capId;

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
    public void getCaps(final VolleyCallback callback) {
        throw new UnsupportedOperationException("getCaps() sin ID no está soportado en ApiMangaDex.");
    }

    @Override
    public void getCap(final VolleyCallback callback) {
        throw new UnsupportedOperationException("getCap() sin capId no está soportado en ApiMangaDex.");
    }
}