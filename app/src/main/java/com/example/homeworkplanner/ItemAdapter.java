package com.example.homeworkplanner;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<String> itemList;

    public ItemAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflar el diseño de la fila del elemento
        View itemView = inflater.inflate(R.layout.activity_inicio, parent, false);

        // Devolver una nueva instancia de ViewHolder
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Obtener el elemento en la posición especificada
        String item = itemList.get(position);

        // Actualizar la vista del ViewHolder con los datos del elemento
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            // Inicializar las vistas dentro del ViewHolder
            textView = itemView.findViewById(R.id.textView);
        }
    }
}