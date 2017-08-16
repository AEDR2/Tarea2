package mx.aedr2.tarea2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvFecha;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private LinearLayout llFecha;
    private Button btSiguiente;

    //Variables que se van a pasar
    private String nombre;
    private String fechaNacimiento;
    private String telefono;
    private String email;
    private String descripcion;

    //Variables de los elementos
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializacion de variables
        etNombre = (EditText) findViewById(R.id.etNombre);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);

        if(getIntent() != null && getIntent().getExtras() != null) {
            //Codigo que verifica si hay datos antes
            Bundle parametros = getIntent().getExtras();

            nombre = parametros.getString(getResources().getString(R.string.nombreEnviar));
            telefono = parametros.getString(getResources().getString(R.string.telefonoEnviar));
            email = parametros.getString(getResources().getString(R.string.emailEnviar));
            fechaNacimiento = parametros.getString(getResources().getString(R.string.fechaEnviar));
            descripcion = parametros.getString(getResources().getString(R.string.descripcionEnviar));

            etNombre.setText(nombre);
            etTelefono.setText(telefono);
            etEmail.setText(email);
            etDescripcion.setText(descripcion);
            tvFecha.setText(fechaNacimiento);
        }

        tvFecha =(TextView) findViewById(R.id.tvFecha);
        llFecha = (LinearLayout) findViewById(R.id.llFecha);
        //Codigo para que aparezca el calendario
        llFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int anio = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialogo = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Material_Dialog_NoActionBar,
                        onDateSetListener,
                        anio, mes, dia);
                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogo.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes +1;
                Log.d(TAG, "onDataSet: mm/dd/yy: " + mes + "/" + dia + "/" + anio);
                String date = mes + "/" + dia + "/" + anio;
                tvFecha.setText(date);
            }
        };

        //Codigo para pasar a la siguiente ventana
        btSiguiente = (Button) findViewById(R.id.btSiguiente);
        btSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se obtienen los datos de la aplicacion
                nombre = etNombre.getText().toString();
                fechaNacimiento = tvFecha.getText().toString();
                telefono = etTelefono.getText().toString();
                email = etEmail.getText().toString();
                descripcion = etDescripcion.getText().toString();

                if(nombre.equals(""))
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.mensajeFaltaNombre), Toast.LENGTH_SHORT).show();
                else{
                    Intent i = new Intent(MainActivity.this, ConfirmarDatos.class);
                    //Nombre
                    i.putExtra(getResources().getString(R.string.nombreEnviar), nombre);

                    //Fecha de nacimiento
                    if(fechaNacimiento.equals(R.string.fecha)) i.putExtra(getResources().getString(R.string.fechaEnviar), "-");
                    else i.putExtra(getResources().getString(R.string.fechaEnviar), fechaNacimiento);

                    //Telefono
                    if(telefono.equals("")) i.putExtra(getResources().getString(R.string.telefonoEnviar), "-");
                    else i.putExtra(getResources().getString(R.string.telefonoEnviar), telefono);

                    //Email
                    if(email.equals("")) i.putExtra(getResources().getString(R.string.emailEnviar), "-");
                    else i.putExtra(getResources().getString(R.string.emailEnviar), email);

                    //Descripcion
                    if(descripcion.equals("")) i.putExtra(getResources().getString(R.string.descripcionEnviar), "-");
                    else i.putExtra(getResources().getString(R.string.descripcionEnviar), descripcion);

                    startActivity(i);
                }

            }
        });


    }

}
