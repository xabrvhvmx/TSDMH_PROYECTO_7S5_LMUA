package com.example.tsdmh_proyecto_7s5_lmua;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EdadActivity extends AppCompatActivity {

    EditText txtMesNacimiento;
    EditText txtAñoNacimiento;

    Button btnCalcularEdad;

    TextView txtResultadoEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtMesNacimiento = findViewById(R.id.txtMes);
        txtAñoNacimiento = findViewById(R.id.txtAño);

        btnCalcularEdad = findViewById(R.id.btnCalcular);

        txtResultadoEdad = findViewById(R.id.txtResultado);

        btnCalcularEdad.setOnClickListener(v -> {

            int mesNacimiento = Integer.parseInt(
                    txtMesNacimiento.getText().toString());

            int anioNacimiento = Integer.parseInt(
                    txtAñoNacimiento.getText().toString());

            int mesActual = 9;
            int añoActual = 2026;

            int edad = añoActual - anioNacimiento;

            if (mesNacimiento > mesActual) {
                edad = edad - 1;
            }

            txtResultadoEdad.setText("Tu edad es: " + edad);

        });
    }
}