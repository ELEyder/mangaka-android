package com.senati.mangaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.senati.mangaka.services.ApiPirateManga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

public class PirateMangasActivity extends AppCompatActivity {
    GifImageView gifCargando;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangas);
        gifCargando = findViewById(R.id.gifCargando);
        linearLayout = findViewById(R.id.linearLayout);

        gifCargando.setImageResource(R.drawable.waiting);
        gifCargando.setVisibility(View.VISIBLE);

        ApiPirateManga.getInstance(this).getMangaList(new ApiPirateManga.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                gifCargando.setVisibility(View.GONE);
                addMangas(response);
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(PirateMangasActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }
    public void addMangas(String response) {
        Intent intent = new Intent(PirateMangasActivity.this, CapitulosActivity.class);
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray mangaList = jsonResponse.getJSONArray("data");
            int mangaCount = mangaList.length();
            for (int i = 0; i < mangaCount; i++) {

                String id = mangaList.getJSONObject(i).getString("id");
                String title = mangaList.getJSONObject(i).getJSONObject("attributes").getString("title");
                String chapters = mangaList.getJSONObject(i).getJSONObject("attributes").getString("lastChapter");
                String genres = mangaList.getJSONObject(i).getJSONObject("attributes").getString("state");
                View itemView = LayoutInflater.from(PirateMangasActivity.this).inflate(R.layout.button_manga, linearLayout, false);

                TextView txtTitle = itemView.findViewById(R.id.txtTitle);
                txtTitle.setText(title);

                TextView txtChapters = itemView.findViewById(R.id.txtChapters);
                txtChapters.setText("Capítulo: " + chapters);

                TextView txtGenres = itemView.findViewById(R.id.txtGenres);
                txtGenres.setText("Vistas: " + genres);

                itemView.setOnClickListener(v -> {
                    intent.putExtra("TITLE", title);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                });

                linearLayout.addView(itemView);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
}