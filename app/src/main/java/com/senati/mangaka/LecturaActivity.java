package com.senati.mangaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.VolleyError;
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
        String mangaId = getIntent().getStringExtra("MANGA-ID");
        String capId = getIntent().getStringExtra("CAP-ID");

        ApiManga.getInstance(this).getCap(mangaId, capId ,new ApiManga.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                gifCargando.setVisibility(View.GONE);
                getCap(response);
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(LecturaActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getCap(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray capList = jsonResponse.getJSONArray("images");
            int limit = capList.length();

            for (int i = 0; i < limit; i++) {
                String url = capList.getJSONObject(i).getString("image");
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
        }
    }
}