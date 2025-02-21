package com.senati.mangaka.ui.mangas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.senati.mangaka.CapitulosActivity;
import com.senati.mangaka.R;
import com.senati.mangaka.data.ApiMangaDex;
import com.senati.mangaka.data.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

public class MangasActivity extends AppCompatActivity {
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

        ApiMangaDex.getInstance(this).getMangaList(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                gifCargando.setVisibility(View.GONE);
                addMangas(response);
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(MangasActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
    public void addMangas(String response) {
        Intent intent = new Intent(MangasActivity.this, CapitulosActivity.class);
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray mangaList = jsonResponse.getJSONArray("data");
            int mangaCount = mangaList.length();
            for (int i = 0; i < mangaCount; i++) {
                JSONObject attributes = mangaList.getJSONObject(i).getJSONObject("attributes");
                String id = mangaList.getJSONObject(i).getString("id");
                String title = attributes.getJSONObject("title").getString("en");
                String chapters = attributes.optString("lastChapter", "No registrado");
                String genres = attributes.getJSONArray("tags").getJSONObject(0).getJSONObject("attributes").getJSONObject("name").getString("en");
                View itemView = LayoutInflater.from(MangasActivity.this).inflate(R.layout.button_manga, linearLayout, false);

                TextView txtTitle = itemView.findViewById(R.id.txtTitle);
                txtTitle.setText(title);

                TextView txtChapters = itemView.findViewById(R.id.txtChapters);
                txtChapters.setText("Capítulo: " + chapters);

                TextView txtGenres = itemView.findViewById(R.id.txtGenres);
                txtGenres.setText("Género: " + genres);

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