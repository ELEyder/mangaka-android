package com.senati.mangaka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Bienvenido " + getIntent().getStringExtra("USERNAME"));

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
