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
    private EditText etNombre, etEmpresa, etProposito, etDni;
    private Button btnRegistrar;
    private LinearLayout containerVisitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = findViewById(R.id.etNombre);
        etEmpresa = findViewById(R.id.etEmpresa);
        etProposito = findViewById(R.id.etProposito);
        etDni = findViewById(R.id.etDni);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        containerVisitas = findViewById(R.id.containerVisitas);

        // configurar al momento que el boton de registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarVisita();
            }
        });
    }

    private void registrarVisita() {
        // Obtener valores ingresados sin espacios adicionales
        String nombre = etNombre.getText().toString().trim();
        String empresa = etEmpresa.getText().toString().trim();
        String proposito = etProposito.getText().toString().trim();
        String dni = etDni.getText().toString().trim();

        //  validar nombre (solo letras y espacios, no permite números ni símbolos)
        if (TextUtils.isEmpty(nombre)) {
            etNombre.setError("Ingresa el nombre");
            etNombre.requestFocus();
            return;
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            etNombre.setError("El nombre solo debe contener letras");
            etNombre.requestFocus();
            return;
        }

        // validar empresa
        if (TextUtils.isEmpty(empresa)) {
            etEmpresa.setError("Ingresa la empresa");
            etEmpresa.requestFocus();
            return;
        }

        // vlidar propósito (no debe ccntener solo numeros)
        if (TextUtils.isEmpty(proposito)) {
            etProposito.setError("Ingresa el propósito");
            etProposito.requestFocus();
            return;
        }
        if (proposito.matches("[0-9]+")) {
            etProposito.setError("El propósito no puede contener solo números");
            etProposito.requestFocus();
            return;
        }

        // dni (debe tener 8 digitos)
        if (TextUtils.isEmpty(dni)) {
            etDni.setError("Ingresa el DNI");
            etDni.requestFocus();
            return;
        }
        if (dni.length() != 8 || !dni.matches("[0-9]+")) {
            etDni.setError("El DNI debe tener exactamente 8 números");
            etDni.requestFocus();
            return;
        }

        // vista de la lista
        if (containerVisitas != null) {
            TextView nuevaVisita = new TextView(this);
            nuevaVisita.setText("• " + nombre + " - " + empresa + " (" + proposito + ")");
            nuevaVisita.setTextSize(15);
            nuevaVisita.setTextColor(getResources().getColor(android.R.color.black));
            nuevaVisita.setPadding(0, 10, 0, 10);

            containerVisitas.addView(nuevaVisita);
        }

        // 6. Mensaje de éxito y limpiar entradas
        Toast.makeText(this, "Visita registrada con éxito", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etEmpresa.setText("");
        etProposito.setText("");
        etDni.setText("");
        etNombre.requestFocus();
    }
}