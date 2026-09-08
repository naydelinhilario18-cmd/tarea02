package com.example.tarea02;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables de la interfaz
    private EditText etNombre, etEmpresa, etProposito, etDni;
    private Button btnRegistrar;
    private LinearLayout containerVisitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Vincular los elementos del XML con Java
        etNombre = findViewById(R.id.etNombre);
        etEmpresa = findViewById(R.id.etEmpresa);
        etProposito = findViewById(R.id.etProposito);
        etDni = findViewById(R.id.etDni);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        containerVisitas = findViewById(R.id.containerVisitas);

        // 2. Configurar el evento al presionar el botón REGISTRAR VISITA
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarVisita();
            }
        });
    }

    private void registrarVisita() {
        // Obtener los valores ingresados
        String nombre = etNombre.getText().toString().trim();
        String empresa = etEmpresa.getText().toString().trim();
        String proposito = etProposito.getText().toString().trim();
        String dni = etDni.getText().toString().trim();

        // 1. Validar campos vacíos con alerta en pantalla
        if (TextUtils.isEmpty(nombre)) {
            etNombre.setError("Ingresa el nombre");
            etNombre.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(empresa)) {
            etEmpresa.setError("Ingresa la empresa");
            etEmpresa.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(proposito)) {
            etProposito.setError("Ingresa el propósito");
            etProposito.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dni)) {
            etDni.setError("Ingresa el DNI");
            etDni.requestFocus();
            return;
        }

        // 2. Validar que el DNI sea exactamente de 8 dígitos
        if (dni.length() != 8) {
            etDni.setError("El DNI debe tener exactamente 8 dígitos");
            etDni.requestFocus();
            return;
        }

        // 3. Agregar la visita a la lista dinámicamente
        if (containerVisitas != null) {
            TextView nuevaVisita = new TextView(this);
            nuevaVisita.setText("• " + nombre + " - " + empresa + " (" + proposito + ")");
            nuevaVisita.setTextSize(15);
            nuevaVisita.setTextColor(getResources().getColor(android.R.color.black));
            nuevaVisita.setPadding(0, 10, 0, 10);

            containerVisitas.addView(nuevaVisita);
        }

        // Notificación de éxito y limpieza de los campos
        Toast.makeText(this, "Visita registrada con éxito", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etEmpresa.setText("");
        etProposito.setText("");
        etDni.setText("");
        etNombre.requestFocus(); // Regresa el cursor al primer campo
    }
}