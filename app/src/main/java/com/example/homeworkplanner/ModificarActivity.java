package com.example.homeworkplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.homeworkplanner.Controlador.ConexionHelper;
import com.example.homeworkplanner.Controlador.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ModificarActivity extends AppCompatActivity {
    Button cancelar, modificar, buscar, eliminar;
    private EditText id;
    private EditText nombre;
    private EditText descripcion;
    private EditText fecha;
    private EditText ubicacion;
    private EditText hora;
    private RadioGroup radioGroup;
    private String prioridad;
    private Calendar calendar;
    ConexionHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        conn = new ConexionHelper(getApplicationContext(), "Mis_tareas", null, 1);

        id = findViewById(R.id.txtId1);
        nombre = findViewById(R.id.txtNombre2);
        descripcion = findViewById(R.id.txtDescripcion2);
        fecha = findViewById(R.id.txtFecha2);
        ubicacion = findViewById(R.id.txtUbicacion2);
        hora = findViewById(R.id.txtHora2);
        radioGroup = findViewById(R.id.rbPrioridad2);

        buscar = findViewById(R.id.btnBuscar);
        eliminar = findViewById(R.id.btnEliminar);
        modificar = findViewById(R.id.btnModificar);
        cancelar = findViewById(R.id.btnCancelar2);
         // Incorporando nuevos botones.
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
                Intent intent = new Intent(ModificarActivity.this, Inicio.class);
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModificarActivity.this, Inicio.class);
                startActivity(intent);
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarUsuario();
                Intent intent = new Intent(ModificarActivity.this, Inicio.class);
                startActivity(intent);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbAlta2) {
                    Toast.makeText(ModificarActivity.this, "Ha seleccionado prioridad alta para su tarea", Toast.LENGTH_SHORT).show();
                    prioridad = "Alta";
                } else if (checkedId == R.id.rbMedia2) {
                    Toast.makeText(ModificarActivity.this, "Ha seleccionado prioridad media para su tarea", Toast.LENGTH_SHORT).show();
                    prioridad = "Media";
                } else if (checkedId == R.id.rbBaja2){
                    Toast.makeText(ModificarActivity.this, "Ha seleccionado prioridad baja para su tarea", Toast.LENGTH_SHORT).show();
                    prioridad = "Baja";
                }
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

    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {id.getText().toString()};
        try{
            Cursor cursor = db.rawQuery("SELECT " + Utility.COLUMN_TITLE + "," + Utility.COLUMN_DESCRIPTION +
                    "," + Utility.COLUMN_LOCATION + "," + Utility.COLUMN_DATE + "," + Utility.COLUMN_TIME +
                    " FROM " + Utility.TABLE_NAME + " WHERE " + Utility.COLUMN_ID + "=? ", parametros);
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            descripcion.setText(cursor.getString(1));
            ubicacion.setText(cursor.getString(2));
            fecha.setText(cursor.getString(3));
            hora.setText(cursor.getString(4));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "El ID de la tarea NO existe.", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarUsuario(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utility.COLUMN_TITLE, nombre.getText().toString());
        values.put(Utility.COLUMN_DESCRIPTION, descripcion.getText().toString());
        values.put(Utility.COLUMN_LOCATION, ubicacion.getText().toString());
        values.put(Utility.COLUMN_DATE, fecha.getText().toString());
        values.put(Utility.COLUMN_TIME, hora.getText().toString());
        values.put(Utility.COLUMN_PRIORITY, prioridad.toString());

        db.update(Utility.TABLE_NAME, values, Utility.COLUMN_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Tarea actualizada exitosamente.", Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    private void eliminarUsuario(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};

        db.delete(Utility.TABLE_NAME, Utility.COLUMN_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Tarea eliminada exitosamente.",Toast.LENGTH_SHORT).show();
        id.setText("");
        limpiar();
        db.close();
    }

    private void limpiar(){
        nombre.setText("");
        descripcion.setText("");
        ubicacion.setText("");
        fecha.setText("");
        hora.setText("");
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
}