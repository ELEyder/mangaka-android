package com.senati.mangaka.data;

import com.android.volley.VolleyError;

public interface VolleyCallback {
    void onSuccess(String response);
    void onError(VolleyError error);

}
