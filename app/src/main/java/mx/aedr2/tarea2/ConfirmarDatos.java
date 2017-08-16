package mx.aedr2.tarea2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarDatos extends AppCompatActivity {

    //Variables que se van a pasar
    private String nombre;
    private String fechaNacimiento;
    private String telefono;
    private String email;
    private String descripcion;

    //Variables de los elementos
    private TextView tvNombre;
    private TextView tvFecha;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    private Button btEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle parametros = getIntent().getExtras();

       nombre = parametros.getString(getResources().getString(R.string.nombreEnviar));
        telefono = parametros.getString(getResources().getString(R.string.telefonoEnviar));
        email = parametros.getString(getResources().getString(R.string.emailEnviar));
        fechaNacimiento = parametros.getString(getResources().getString(R.string.fechaEnviar));
        descripcion = parametros.getString(getResources().getString(R.string.descripcionEnviar));

        tvNombre = (TextView) findViewById(R.id.tvNombreConfirmacion);
        tvFecha = (TextView) findViewById(R.id.tvFechaConfirmacion);
        tvTelefono = (TextView) findViewById(R.id.tvTelefonoConfirmacion);
        tvEmail = (TextView) findViewById(R.id.tvEmailConfirmacion);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcionConfirmacion2);

        tvNombre.setText(nombre);
        tvTelefono.setText(tvTelefono.getText().toString() + telefono);
        tvEmail.setText(tvEmail.getText().toString() + email);
        tvFecha.setText(tvFecha.getText().toString() + fechaNacimiento);
        tvDescripcion.setText(descripcion);

        //Codigo del boton
        btEditar = (Button) findViewById(R.id.btEditar);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ConfirmarDatos.this, MainActivity.class);
                //Nombre
                i.putExtra(getResources().getString(R.string.nombreEnviar), nombre);

                //Fecha de nacimiento
                if(fechaNacimiento.equals(R.string.fecha)) i.putExtra(getResources().getString(R.string.fechaEnviar), R.string.fecha);
                else i.putExtra(getResources().getString(R.string.fechaEnviar), fechaNacimiento);

                //Telefono
                if(telefono.equals(null)) i.putExtra(getResources().getString(R.string.telefonoEnviar), R.string.telefono);
                else i.putExtra(getResources().getString(R.string.telefonoEnviar), telefono);

                //Email
                if(email.equals(null)) i.putExtra(getResources().getString(R.string.emailEnviar), R.string.email);
                else i.putExtra(getResources().getString(R.string.emailEnviar), email);

                //Descripcion
                if(descripcion.equals(null)) i.putExtra(getResources().getString(R.string.descripcionEnviar), R.string.descripcion);
                else i.putExtra(getResources().getString(R.string.descripcionEnviar), descripcion);

                startActivity(i);
            }
        });
    }
}
