package com.senati.mangaka.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.senati.mangaka.ui.mangas.MangasActivity;
import com.senati.mangaka.R;
import com.senati.mangaka.ui.mangas.PirateMangasActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnPirateMangas, btnMangas, btnLibros, btnComics;
    private TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPirateMangas = findViewById(R.id.btnPirateMangas);
        btnMangas = findViewById(R.id.btnMangas);
        btnLibros = findViewById(R.id.btnLibros);
        btnComics = findViewById(R.id.btnComics);

        txtTitle = findViewById(R.id.txt);

        String username = getIntent().getStringExtra("USERNAME");
        if (username.equals("") || username == null) username = "Usuario";
        txtTitle.setText("Bienvenido " + username);

        btnPirateMangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PirateMangasActivity.class);
                startActivity(intent);
            }
        });

        btnMangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MangasActivity.class);
                startActivity(intent);
            }
        });
        btnLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this, "Próximamente", Toast.LENGTH_SHORT).show();
            }
        });
        btnComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this, "Próximamente", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
