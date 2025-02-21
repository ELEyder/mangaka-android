package com.senati.mangaka;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.senati.mangaka.services.ApiManga;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

public class LecturaActivity extends AppCompatActivity {

    TextView txtTitle;
    GifImageView gifCargando;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura);

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(getIntent().getStringExtra("TITLE"));
        gifCargando = findViewById(R.id.gifCargando);
        gifCargando.setImageResource(R.drawable.waiting);
        gifCargando.setVisibility(View.VISIBLE);
        linearLayout = findViewById(R.id.linearLayout);

        String capId = getIntent().getStringExtra("CAP-ID");

        ApiManga.getInstance(this).getCap(capId ,new ApiManga.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                gifCargando.setVisibility(View.GONE);
                getCap(response);
            }

            @Override
            public void onError(VolleyError error) {
                String errorMessage = "Error: " + error.toString();
                if (error.networkResponse != null) {
                    errorMessage += "\nCódigo de estado: " + error.networkResponse.statusCode;
                    if (error.networkResponse.data != null) {
                        errorMessage += "\nRespuesta: " + new String(error.networkResponse.data);
                    }
                }
                Toast.makeText(LecturaActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

    }

    private void getCap(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            String base = jsonResponse.getString("baseUrl");
            JSONObject chapter = jsonResponse.getJSONObject("chapter");
            String hash = chapter.getString("hash");
            JSONArray capsList = chapter.getJSONArray("data");

            int limit = capsList.length();

            for (int i = 0; i < limit; i++) {

                String url = base + "/data/" + hash + "/" + capsList.getString(i);
                ImageView imgTemp = new ImageView(this);
                imgTemp.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                imgTemp.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Picasso.get().load(url).into(imgTemp);
                linearLayout.addView(imgTemp);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos del capítulo", Toast.LENGTH_LONG).show();
        }
    }
}