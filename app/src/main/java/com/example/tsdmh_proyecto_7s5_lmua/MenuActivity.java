package com.example.tsdmh_proyecto_7s5_lmua;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    Button btnrfc;
    Button btnedad;
    Button btninfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnrfc = findViewById(R.id.btnrfc);
        btnedad = findViewById(R.id.btnedad);
        btninfo = findViewById(R.id.btninfo);

        btnrfc.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, RFCActivity.class);
            startActivity(intent);
        });

        btnedad.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, EdadActivity.class);
            startActivity(intent);
        });

        btninfo.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, AcercaActivity.class);
            startActivity(intent);
        });

    }
}