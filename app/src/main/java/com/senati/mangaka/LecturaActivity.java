package com.senati.mangaka;

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
import com.senati.mangaka.data.ApiManga;
import com.senati.mangaka.data.ApiMangaDex;
import com.senati.mangaka.data.ApiPirateManga;
import com.senati.mangaka.data.VolleyCallback;
import com.senati.mangaka.ui.mangas.PirateMangasActivity;
import com.senati.mangaka.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String type = getIntent().getStringExtra("TYPE");


        ApiManga api = type.equals("pirate")
                ? ApiPirateManga.getInstance(this)
                : ApiMangaDex.getInstance(this);

        api.getCap(new VolleyCallback() {
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

            JSONObject feed = jsonResponse.getJSONObject("feed");

            JSONArray entries = feed.getJSONArray("entry");

            for (int i = 0; i < entries.length(); i++) {

                JSONObject entry = entries.getJSONObject(i);

                String title = entry.getJSONObject("title").getString("$t");

                if (!title.equals(getIntent().getStringExtra("TITLE"))) continue;

                String img = entry.getJSONObject("content").getString("$t");

                String regex = "src=\\\"(.*?)\\\"";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(img);

                while (matcher.find()) {

                    String url = matcher.group(1);
                    ImageView imgTemp = new ImageView(this);

                    imgTemp.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    imgTemp.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Picasso.get().load(url).into(imgTemp);
                    linearLayout.addView(imgTemp);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos del capítulo", Toast.LENGTH_LONG).show();
        }
    }
}