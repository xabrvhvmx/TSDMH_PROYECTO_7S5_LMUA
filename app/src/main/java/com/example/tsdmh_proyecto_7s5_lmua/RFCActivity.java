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

public class RFCActivity extends AppCompatActivity {

    EditText txtNombre, txtPaterno, txtMaterno;
    EditText txtDia, txtMes, txtAño;
    Button btnGenerarRFC;
    TextView txtResultadoRFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rfcactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.txtnombre);
        txtPaterno = findViewById(R.id.txtpaterno);
        txtMaterno = findViewById(R.id.txtmaterno);

        txtDia = findViewById(R.id.txtnacimiento);
        txtMes = findViewById(R.id.txtmes);
        txtAño = findViewById(R.id.txtaño);

        btnGenerarRFC = findViewById(R.id.btngenerar);
        txtResultadoRFC = findViewById(R.id.resultado);

        btnGenerarRFC.setOnClickListener(v -> {

            String nombre = txtNombre.getText().toString().trim();
            String paterno = txtPaterno.getText().toString().trim();
            String materno = txtMaterno.getText().toString().trim();

            String dia = txtDia.getText().toString().trim();
            String mes = txtMes.getText().toString().trim();
            String año = txtAño.getText().toString().trim();

            if (nombre.isEmpty() ||
                    paterno.isEmpty() ||
                    materno.isEmpty() ||
                    dia.isEmpty() ||
                    mes.isEmpty() ||
                    año.isEmpty()) {

                txtResultadoRFC.setText("Completa todos los datos");
                return;
            }

            try {

                String rfc = generarRFC(
                        nombre,
                        paterno,
                        materno,
                        dia,
                        mes,
                        año
                );

                txtResultadoRFC.setText(rfc);

            } catch (Exception e) {

                txtResultadoRFC.setText(
                        "Verifica que la fecha sea correcta"
                );
            }
        });
    }

    private String generarRFC(
            String nombre,
            String paterno,
            String materno,
            String dia,
            String mes,
            String año) {

        nombre = limpiarTexto(nombre);
        paterno = limpiarTexto(paterno);
        materno = limpiarTexto(materno);

        // ------------------------------------------
        // APELLIDO PATERNO
        // ------------------------------------------

        String[] palabrasPaterno = paterno.split("\\s+");

        int posicionApellido =
                obtenerPosicionApellido(palabrasPaterno);

        String apellidoPrincipal =
                palabrasPaterno[posicionApellido];

        String primeraLetraPaterno =
                apellidoPrincipal.substring(0, 1);

        String primeraVocalPaterno =
                obtenerPrimeraVocalInterna(apellidoPrincipal);

        // ------------------------------------------
        // APELLIDO MATERNO
        // ------------------------------------------

        String[] palabrasMaterno = materno.split("\\s+");

        int posicionMaterno =
                obtenerPosicionApellido(palabrasMaterno);

        String apellidoMaternoPrincipal =
                palabrasMaterno[posicionMaterno];

        String primeraLetraMaterno =
                apellidoMaternoPrincipal.substring(0, 1);

        // ------------------------------------------
        // NOMBRE
        // ------------------------------------------

        String[] nombres = nombre.split("\\s+");

        String primeraLetraNombre =
                obtenerPrimeraLetraNombre(nombres);

        // ------------------------------------------
        // FECHA
        // ------------------------------------------

        String añoRFC;

        if (año.length() >= 2) {
            añoRFC = año.substring(año.length() - 2);
        } else {
            añoRFC = String.format(
                    "%02d",
                    Integer.parseInt(año)
            );
        }

        String mesRFC =
                String.format(
                        "%02d",
                        Integer.parseInt(mes)
                );

        String diaRFC =
                String.format(
                        "%02d",
                        Integer.parseInt(dia)
                );

        // ------------------------------------------
        // RESULTADO
        // ------------------------------------------

        return primeraLetraPaterno
                + primeraVocalPaterno
                + primeraLetraMaterno
                + primeraLetraNombre
                + añoRFC
                + mesRFC
                + diaRFC;
    }

    // ------------------------------------------
    // OBTENER APELLIDO PRINCIPAL
    // ------------------------------------------

    private int obtenerPosicionApellido(String[] palabras) {

        if (palabras.length == 0) {
            return 0;
        }

        // DE LA CRUZ
        // DE LOS SANTOS
        // DE LAS CASAS

        if (palabras.length >= 3) {

            if (palabras[0].equals("DE") &&
                    (palabras[1].equals("LA") ||
                            palabras[1].equals("LOS") ||
                            palabras[1].equals("LAS"))) {

                return 2;
            }
        }

        // DEL RIO
        // DEL VALLE

        if (palabras.length >= 2 &&
                palabras[0].equals("DEL")) {

            return 1;
        }


        if (palabras.length >= 2 &&
                palabras[0].equals("DE")) {

            return 1;
        }

        if (palabras.length >= 2 &&
                (palabras[0].equals("LA") ||
                        palabras[0].equals("LOS") ||
                        palabras[0].equals("LAS"))) {

            return 1;
        }

        return 0;
    }


    private String obtenerPrimeraVocalInterna(String apellido) {

        if (apellido.length() <= 1) {
            return "X";
        }

        for (int i = 1; i < apellido.length(); i++) {

            char letra = apellido.charAt(i);

            if ("AEIOU".indexOf(letra) >= 0) {

                return String.valueOf(letra);
            }
        }

        return "X";
    }


    private String obtenerPrimeraLetraNombre(String[] nombres) {

        if (nombres.length == 0) {
            return "X";
        }

        return nombres[0].substring(0, 1);
    }

    private String limpiarTexto(String texto) {

        return texto
                .toUpperCase()
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("Ü", "U")
                .trim()
                .replaceAll("\\s+", " ");
    }
}