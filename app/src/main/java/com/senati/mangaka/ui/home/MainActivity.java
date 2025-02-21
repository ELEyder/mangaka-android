package com.senati.mangaka.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.senati.mangaka.R;
import com.senati.mangaka.ui.menu.MenuActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsername;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("USERNAME" , username);
                startActivity(intent);
            }
        });

    }


}

