package com.example.homeworkplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.homeworkplanner.Controlador.ConexionHelper;
import com.google.android.material.floatingactionbutton.*;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {
    FloatingActionButton aniadir, actualizar, help;
    RecyclerView listaTareas;
    ArrayList<String> tarea_id, tarea_titulo, tarea_descripcion, tarea_ubicacion, tarea_fecha, tarea_hora, tarea_prioridad;
    ConexionHelper conn;
    CustomAdapter cada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        aniadir = findViewById(R.id.buttonAdd);
        listaTareas = findViewById(R.id.rvLista);
        actualizar = findViewById(R.id.buttonUpdate);
        help = findViewById(R.id.buttonHelp);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearTarea.class);
                startActivity(intent);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ModificarActivity.class);
                startActivity(intent);
            }
        });

        conn = new ConexionHelper(getApplicationContext(),"Mis_tareas", null,1);
        tarea_id = new ArrayList<>();
        tarea_titulo = new ArrayList<>();
        tarea_descripcion = new ArrayList<>();
        tarea_ubicacion = new ArrayList<>();
        tarea_fecha = new ArrayList<>();
        tarea_hora = new ArrayList<>();
        tarea_prioridad = new ArrayList<>();
        storeDataInArrays();

        cada = new CustomAdapter(Inicio.this,this, tarea_id, tarea_titulo, tarea_descripcion, tarea_ubicacion,  tarea_fecha, tarea_hora, tarea_prioridad);
        listaTareas.setAdapter(cada);
        listaTareas.setLayoutManager(new LinearLayoutManager(Inicio.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }



    void storeDataInArrays() {
        Cursor cursor = conn.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                tarea_id.add(cursor.getString(0));
                tarea_titulo.add(cursor.getString(1));
                tarea_descripcion.add(cursor.getString(2));
                tarea_ubicacion.add(cursor.getString(3));
                tarea_fecha.add(cursor.getString(4));
                tarea_hora.add(cursor.getString(5));
                tarea_prioridad.add(cursor.getString(6));
            }
        }
    }
}