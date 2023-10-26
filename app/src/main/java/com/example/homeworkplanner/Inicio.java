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

import com.google.android.material.floatingactionbutton.*;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {
    FloatingActionButton aniadir;
    RecyclerView listaTareas;
    MyDatabaseHelper myDB;
    ArrayList<String> tarea_id, tarea_titulo, tarea_descripcion, tarea_ubicacion, tarea_fecha, tarea_hora;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        aniadir = findViewById(R.id.buttonAdd);
        listaTareas = findViewById(R.id.rvLista);
        aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearTarea.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(Inicio.this);
        tarea_id = new ArrayList<>();
        tarea_titulo = new ArrayList<>();
        tarea_descripcion = new ArrayList<>();
        tarea_ubicacion = new ArrayList<>();
        tarea_fecha = new ArrayList<>();
        tarea_hora = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(Inicio.this,tarea_id, tarea_titulo, tarea_fecha, tarea_hora);
        listaTareas.setAdapter(customAdapter);
        listaTareas.setLayoutManager(new LinearLayoutManager(Inicio.this));
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                tarea_id.add(cursor.getString(0));
                tarea_titulo.add(cursor.getString(1));
                tarea_descripcion.add(cursor.getString(2));
                tarea_ubicacion.add(cursor.getString(3));
                tarea_fecha.add(cursor.getString(4));
                tarea_hora.add(cursor.getString(5));
            }
        }
    }

}