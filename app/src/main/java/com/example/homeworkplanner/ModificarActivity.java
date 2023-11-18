package com.example.homeworkplanner;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Calendar;

public class ModificarActivity extends AppCompatActivity {
    Button cancelar, modificar, buscar, eliminar;
    private EditText id;
    private EditText nombre;
    private EditText descripcion;
    private EditText fecha;
    private EditText ubicacion;
    private EditText hora;
    private RadioGroup radioGroup;
    private Calendar calendar;
    ConexionHelper conn;
    String str_id, str_titulo, str_descripcion, str_ubicacion, str_fecha, str_hora;
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
            Toast.makeText(getApplicationContext(), "ATENCIÓN, Usuario no existe.", Toast.LENGTH_SHORT).show();
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

        db.update(Utility.TABLE_NAME, values, Utility.COLUMN_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCIÓN, se actualizó el usuario", Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    private void eliminarUsuario(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};

        db.delete(Utility.TABLE_NAME, Utility.COLUMN_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCIÓN, se eliminó el usuario",Toast.LENGTH_SHORT).show();
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
}