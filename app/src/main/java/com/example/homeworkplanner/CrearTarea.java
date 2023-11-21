package com.example.homeworkplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkplanner.Controlador.ConexionHelper;
import com.example.homeworkplanner.Controlador.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CrearTarea extends AppCompatActivity {
    Button cancelar;
    Button crear;
    private EditText nombre;
    private EditText descripcion;
    private EditText fecha;
    private EditText ubicacion;
    private EditText hora;
    private String prioridad;
    private RadioGroup radioGroup;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        // Obtener referencias a los elementos de la interfaz
        nombre = findViewById(R.id.txtNombre);
        descripcion = findViewById(R.id.txtDescripcion);
        fecha = findViewById(R.id.txtFecha);
        ubicacion = findViewById(R.id.txtUbicacion);
        hora = findViewById(R.id.txtHora);

        crear = findViewById(R.id.btnCrear);
        cancelar = findViewById(R.id.btnCancelar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                startActivity(intent);
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuarios();
                Intent intent = new Intent(CrearTarea.this, Inicio.class);
                startActivity(intent);
            }
        });



        calendar = Calendar.getInstance();

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDatePicker();
            }
        });

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTimePicker();
            }
        });

        radioGroup = findViewById(R.id.rbPrioridad);
        //Método al seleccionar uno de los radiobutton
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbAlta) {
                    Toast.makeText(CrearTarea.this, "Ha seleccionado prioridad alta para su tarea", Toast.LENGTH_SHORT).show();
                    prioridad = "Alta";
                } else if (checkedId == R.id.rbMedia) {
                    Toast.makeText(CrearTarea.this, "Ha seleccionado prioridad media para su tarea", Toast.LENGTH_SHORT).show();
                    prioridad = "Media";
                } else if (checkedId == R.id.rbBaja){
                    Toast.makeText(CrearTarea.this, "Ha seleccionado prioridad baja para su tarea", Toast.LENGTH_SHORT).show();
                    prioridad = "Baja";
                }
            }
        });
    }
    //Metodo de fecha
    private void mostrarDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // Muestra el diálogo del selector de fecha
        datePickerDialog.show();
    }

    //Método para el formato de la fecha
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
            // Actualiza el calendario con la fecha seleccionada
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            // Formato de la fecha seleccionada y mostrar en el EditText
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            fecha.setText(dateFormat.format(calendar.getTime()));
        }
    };

    //Método para desplegar un calendario para el campo Fecha estimada
    private void mostrarTimePicker() {
        int horaDelDia = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                timeSetListener,
                horaDelDia,
                minuto,
                true //Mostrar formato de 24 horas
        );

        // Muestra el diálogo del selector de hora
        timePickerDialog.show();
    }

    //Método para desplegar un selector de hora para el campo Hora
    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            // Actualiza el calendario con la hora estimada seleccionada.
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            // Formato de la hora estimada seleccionada y mostrada en el EditText.
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            hora.setText(timeFormat.format(calendar.getTime()));
        }
    };
    private void registrarUsuarios(){
        ConexionHelper conn = new ConexionHelper(this,"Mis_tareas", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Utility.COLUMN_TITLE,nombre.getText().toString());
        contentValues.put(Utility.COLUMN_DESCRIPTION,descripcion.getText().toString());
        contentValues.put(Utility.COLUMN_LOCATION,ubicacion.getText().toString());
        contentValues.put(Utility.COLUMN_DATE,fecha.getText().toString());
        contentValues.put(Utility.COLUMN_TIME,hora.getText().toString());
        contentValues.put(Utility.COLUMN_PRIORITY,prioridad.toString());

        Long idResultante = db.insert(Utility.TABLE_NAME,Utility.COLUMN_ID,contentValues);
        Toast.makeText(getApplicationContext(), "Tarea creada exitosamente." + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }
}