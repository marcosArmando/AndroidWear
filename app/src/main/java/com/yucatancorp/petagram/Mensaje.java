package com.yucatancorp.petagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Mensaje extends AppCompatActivity {

    private TextView nombre;
    private TextView correo;
    private TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        nombre = (TextView) findViewById(R.id.nombre_usuario);
        correo = (TextView) findViewById(R.id.correo_usuario);
        mensaje = (TextView) findViewById(R.id.mensaje_correo);
    }

    public void enviarMail(View view){
        String mensajeNombre = nombre.getText().toString();
        String mensajeMensaje = mensaje.getText().toString();
        String miCorreo = "marcos@correo.mx";
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{miCorreo});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mensajeNombre);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensajeMensaje);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));


    }

}
