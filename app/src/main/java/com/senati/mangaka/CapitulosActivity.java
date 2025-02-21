package com.senati.mangaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.VolleyError;
import com.senati.mangaka.services.ApiManga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class CapitulosActivity extends AppCompatActivity {
    TextView txtManga;
    GifImageView gifCargando;
    LinearLayout linearLayout;
    String mangaId;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulos);

        txtManga = findViewById(R.id.txtTitle);
        txtManga.setText(getIntent().getStringExtra("TITLE"));

        gifCargando = findViewById(R.id.gifCargando);
        gifCargando.setImageResource(R.drawable.waiting);
        gifCargando.setVisibility(View.VISIBLE);


        linearLayout = findViewById(R.id.linearLayout);
        mangaId = getIntent().getStringExtra("ID");
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<View> views = new ArrayList<>();
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    views.add(linearLayout.getChildAt(i));
                }

                linearLayout.removeAllViews();

                Collections.reverse(views);

                for (View viewCap : views) linearLayout.addView(viewCap);
            }
        });
        ApiManga.getInstance(this).getCaps(mangaId ,new ApiManga.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                gifCargando.setVisibility(View.GONE);
                getCaps(response);
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(CapitulosActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }
    public void getCaps(String response) {
        Intent intent = new Intent(CapitulosActivity.this, LecturaActivity.class);
        try {

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray capList = jsonResponse.getJSONArray("data");

            int limit = capList.length();
            for (int i = 0; i < limit; i++) {

                JSONObject attributes = capList.getJSONObject(i).getJSONObject("attributes");

                String id = capList.getJSONObject(i).getString("id");
                String title = attributes.optString("title", "Sin capítulo");
                String volume = attributes.optString("volume", "Sin Volumen");
                String chapter = attributes.optString("chapter", "Sin Capítulo");

                View itemView = LayoutInflater.from(CapitulosActivity.this).inflate(R.layout.button_cap, linearLayout, false);

                TextView txtTitle = itemView.findViewById(R.id.txtTitle);
                txtTitle.setText(title);

                TextView txtChapters = itemView.findViewById(R.id.txtChapters);
                txtChapters.setText("Volumen: " + volume);

                TextView txtGenres = itemView.findViewById(R.id.txtGenres);
                txtGenres.setText("Capítulo: " + chapter);

                itemView.setOnClickListener(v -> {
                    intent.putExtra("CAP-ID", id);
                    intent.putExtra("TITLE", title);
                    startActivity(intent);
                });

                linearLayout.addView(itemView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}