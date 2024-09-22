package com.senati.mangaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import com.android.volley.VolleyError;

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
            JSONArray capList = jsonResponse.getJSONArray("chapterList");
            int limit = capList.length();
            for (int i = 0; i < limit; i++) {

                String id = capList.getJSONObject(i).getString("id");
                String title = capList.getJSONObject(i).getString("name");
                String view = capList.getJSONObject(i).getString("view");
                String createdAt = capList.getJSONObject(i).getString("createdAt");

                View itemView = LayoutInflater.from(CapitulosActivity.this).inflate(R.layout.button_cap, linearLayout, false);

                TextView txtTitle = itemView.findViewById(R.id.txtTitle);
                txtTitle.setText(title);

                TextView txtChapters = itemView.findViewById(R.id.txtChapters);
                txtChapters.setText("Vistas: " + view);

                TextView txtGenres = itemView.findViewById(R.id.txtGenres);
                txtGenres.setText("Subido: " + createdAt);

                itemView.setOnClickListener(v -> {
                    intent.putExtra("CAP-ID", id);
                    intent.putExtra("MANGA-ID", mangaId);
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