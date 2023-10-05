package com.example.homeworkplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {
    FloatingActionButton aniadir;
    RecyclerView listaTareas;
    List<String> itemList;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        aniadir = findViewById(R.id.buttonAdd);
        aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearTarea.class);
                startActivity(intent);
            }
        });

        listaTareas = findViewById(R.id.rvLista);
        listaTareas.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        adapter = new ItemAdapter(itemList);
        listaTareas.setAdapter(adapter);


    }

    public void addItem(View view) {
        Intent intent = new Intent(this, CrearTarea.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                String newItem = data.getStringExtra("newItem");
                itemList.add(newItem);
                adapter.notifyDataSetChanged();
            }
        }
    }

}