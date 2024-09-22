package com.senati.mangaka;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;

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

        ApiManga.getInstance(this).getMangaList(new ApiManga.VolleyCallback() {
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
            JSONArray mangaList = jsonResponse.getJSONArray("mangaList");
            int mangaCount = mangaList.length();
            for (int i = 0; i < mangaCount; i++) {

                String id = mangaList.getJSONObject(i).getString("id");
                String title = mangaList.getJSONObject(i).getString("title");
                String chapters = mangaList.getJSONObject(i).getString("chapter");
                String genres = mangaList.getJSONObject(i).getString("view");
                String url = mangaList.getJSONObject(i).getString("image");
                View itemView = LayoutInflater.from(MangasActivity.this).inflate(R.layout.button_manga, linearLayout, false);

                TextView txtTitle = itemView.findViewById(R.id.txtTitle);
                txtTitle.setText(title);

                TextView txtChapters = itemView.findViewById(R.id.txtChapters);
                txtChapters.setText("Capítulo: " + chapters);

                TextView txtGenres = itemView.findViewById(R.id.txtGenres);
                txtGenres.setText("Vistas: " + genres);

                ImageView imgManga = itemView.findViewById(R.id.imgManga);
                Picasso.get().load(url).into(imgManga);

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