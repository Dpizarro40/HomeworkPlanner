package com.example.homeworkplanner;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList txt_id, txt_titulo, txt_fecha, txt_hora;
    CustomAdapter(Context context, ArrayList txt_id, ArrayList txt_titulo, ArrayList txt_fecha, ArrayList txt_hora){
        this.context = context;
        this.txt_id = txt_id;
        this.txt_titulo = txt_titulo;
        this.txt_fecha = txt_fecha;
        this.txt_hora = txt_hora;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtIdView.setText(String.valueOf(txt_id.get(position)));
        holder.txtTituloView.setText(String.valueOf(txt_titulo.get(position)));
        holder.txtFechaView.setText(String.valueOf(txt_fecha.get(position)));
        holder.txtHoraView.setText(String.valueOf(txt_hora.get(position)));
    }

    @Override
    public int getItemCount() {
        return txt_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdView,txtTituloView,txtFechaView,txtHoraView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdView = itemView.findViewById(R.id.tarea_id_txt);
            txtTituloView = itemView.findViewById(R.id.tarea_titulo_txt);
            txtFechaView = itemView.findViewById(R.id.tarea_fecha_txt);
            txtHoraView = itemView.findViewById(R.id.tarea_hora_txt);

        }
    }
}
