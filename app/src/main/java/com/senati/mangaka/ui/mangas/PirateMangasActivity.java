package com.senati.mangaka.ui.mangas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.senati.mangaka.LecturaActivity;
import com.senati.mangaka.R;
import com.senati.mangaka.data.ApiPirateManga;
import com.senati.mangaka.data.VolleyCallback;
import com.senati.mangaka.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        ApiPirateManga.getInstance(this).getMangaList(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                gifCargando.setVisibility(View.GONE);
                addMangas(response);
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
                Toast.makeText(PirateMangasActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });


    }
    public void addMangas(String response) {
        Intent intent = new Intent(PirateMangasActivity.this, LecturaActivity.class);

        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject feed = jsonResponse.getJSONObject("feed");
            JSONArray entries = feed.getJSONArray("entry");

            Log.d("ENTRIES_LENGTH", "Total de mangas: " + entries.length());

            for (int i = 0; i < entries.length(); i++) {
                JSONObject entry = entries.getJSONObject(i);
                JSONArray alternativeLinks = entry.getJSONArray("link");

                String title = entry.getJSONObject("title").getString("$t");
                String category = entry.getJSONArray("category").getJSONObject(0).getString("term");
                String published = Utils.formatearFecha(entry.getJSONObject("published").getString("$t"));
                String img = entry.getJSONObject("content").getString("$t");

                Pattern pattern = Pattern.compile("src=\\\"(.*?)\\\"");
                Matcher matcher = pattern.matcher(img);

                img = matcher.find() ? matcher.group(1) : null;

                String url = null;
                for (int k = 0; k < alternativeLinks.length(); k++) {
                    JSONObject link = alternativeLinks.getJSONObject(k);
                    if ("alternate".equals(link.getString("rel"))) {
                        url = link.getString("href");
                        break;
                    }
                }

                View itemView = LayoutInflater.from(PirateMangasActivity.this)
                        .inflate(R.layout.button_manga, linearLayout, false);

                TextView txtTitle = itemView.findViewById(R.id.txtTitle);
                TextView txtChapters = itemView.findViewById(R.id.txtChapters);
                TextView txtGenres = itemView.findViewById(R.id.txtGenres);
                ImageView imgManga = itemView.findViewById(R.id.imgManga);

                txtTitle.setText(title != null ? title : "Título no disponible");
                txtChapters.setText("Categoría: " + (category != null ? category : "Desconocida"));
                txtGenres.setText("Publicado: " + (published != null ? published : "Desconocido"));

                Picasso.get().load(img).into(imgManga);

                String finalUrl = url;
                itemView.setOnClickListener(v -> {
                    intent.putExtra("TITLE", title);
                    intent.putExtra("TYPE", "pirate");
                    intent.putExtra("URL", finalUrl);
                    startActivity(intent);
                });

                linearLayout.addView(itemView);
            }

        } catch (JSONException e) {
            Log.e("JSON_ERROR", "Error procesando el JSON", e);
        }
    }

}