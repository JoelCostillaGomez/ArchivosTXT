package com.jcg.itics.tesoem.edu.archivostxt;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre;
    TextView lblmostrar;

    private final String nomarch = "datosJCG.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        lblmostrar = findViewById(R.id.lblmostrar);
        CargarInfo();
    }

    public void MGrabar (View v){
        ManejoArchivoTXT controlador = new ManejoArchivoTXT();
        String Texto="";
        try {
            Texto=txtnombre.getText().toString();
            controlador.agregar(Texto,TextoCompleto);
            TextoCompleto = controlador.getContenido();
            if (controlador.grabar(TextoCompleto, this, nomarch));
            {
                Toast.makeText(this, "Se grabo Correctamente", Toast.LENGTH_SHORT).show();
                CargarInfo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Mleer(View v) {
        CargarInfo();
    }

    public void CargarInfo(){
        ManejoArchivoTXT objmanarch = new ManejoArchivoTXT();
        if (objmanarch.verificar(this, nomarch)){
            Toast.makeText(this,"Existe el archivo...", Toast.LENGTH_SHORT).show();
            if(objmanarch.leer(this, nomarch)){
                TextoCompleto = objmanarch.getContenido();
                String cadena="";
                for (String micadena : TextoCompleto) {
                    cadena += "\n" + micadena;
                }

                lblmostrar.setText(cadena);
            }
        }else{
            Toast.makeText(this, "No exite Archivo...", Toast.LENGTH_LONG).show();
        }
    }
}