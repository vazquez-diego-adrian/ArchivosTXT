package avd.itics.tesoem.edu.archivostxt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre;
    TextView lblmostrar;

    private  final String nomarch = "datosVDA.tx";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        lblmostrar = findViewById(R.id.lblmostrar);

        ManejoArchivoTXT objmanarch = new ManejoArchivoTXT();
        if( objmanarch.verificar(this,nomarch)){
            Toast.makeText(this, "Existe el archivo...", Toast.LENGTH_SHORT).show();
            if (objmanarch.leer(this,nomarch)){
                TextoCompleto = objmanarch.getContenido();
                String cadena = "";
                for ( String micadena : TextoCompleto){
                    cadena+="\n "+ micadena;
                }
                lblmostrar.setText(cadena);
            }
        }else{
            Toast.makeText(this, " No existe el archivo...", Toast.LENGTH_SHORT).show();
        }
    }

    public void MGrabar(View v){
        ManejoArchivoTXT controlador = new ManejoArchivoTXT();
        String Texto = "";
        try {
            Texto = txtnombre.getText().toString();
                    controlador.agregar(Texto,TextoCompleto);
                    TextoCompleto = controlador.getContenido();
            if(controlador.grabar(TextoCompleto,this, nomarch)){
                Toast.makeText(this, "Ya se grabo wey...", Toast.LENGTH_SHORT).show();
                CargarInfo();
            }else
                {
                Toast.makeText(this, "No mames no se grabo...", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void MLeer (View v){
        CargarInfo();
    }
    private void CargarInfo(){
        ManejoArchivoTXT objmanarch = new ManejoArchivoTXT();
        if( objmanarch.verificar(this,nomarch)){
            Toast.makeText(this, "Existe el archivo...", Toast.LENGTH_SHORT).show();
            if (objmanarch.leer(this,nomarch)){
                TextoCompleto = objmanarch.getContenido();
                String cadena = "";
                for ( String micadena : TextoCompleto){
                    cadena+="\n"+micadena;
                }
                lblmostrar.setText(cadena);
            }
        }else{
            Toast.makeText(this, " No existe el archivo...", Toast.LENGTH_SHORT).show();
        }
    }
}
