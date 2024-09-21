package com.senati.mangaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnMangas, btnLibros, btnComics;
    private TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMangas = findViewById(R.id.btnMangas);
        btnLibros = findViewById(R.id.btnLibros);
        btnComics = findViewById(R.id.btnComics);

        txtTitle = findViewById(R.id.txt);
        String username = getIntent().getStringExtra("USERNAME");
        if (username.equals("") || username == null) username = "usuario";
        txtTitle.setText("Bienvenido " + username);

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
