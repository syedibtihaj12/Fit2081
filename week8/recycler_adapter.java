package com.example.warehouseinventoryapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehouseinventoryapp.provider.DeleteItemInterfaceListener;
import com.example.warehouseinventoryapp.provider.itemViewModel;
import com.example.warehouseinventoryapp.provider.item_table_schema;

import java.util.ArrayList;
import java.util.List;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.ViewHolder> {

    List<item_table_schema> data;
    DeleteItemInterfaceListener delete_listener;


    public recycler_adapter(List<item_table_schema> data, DeleteItemInterfaceListener delete_listener) {
        this.data = data;
        this.delete_listener = delete_listener;
    }

    public void setData(List<item_table_schema> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_id.setText(String.valueOf(data.get(position).getId()));
        holder.item_name.setText(data.get(position).getItem_name());
        holder.quantity.setText(String.valueOf(data.get(position).getQuantity()));
        holder.cost.setText(String.valueOf(data.get(position).getCost()));
        holder.description.setText(data.get(position).getDescription());
        holder.isfrozen.setText((data.get(position).getIs_frozen()));

        holder.delete_button.setOnClickListener(View -> {
            delete_listener.onDeleteitemClick(data.get(position).getId());
        });

    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_id;
        TextView item_name;
        TextView quantity;
        TextView cost;
        TextView description;
        TextView isfrozen;
        Button delete_button;

        public ViewHolder(@NonNull View item_view) {
            super(item_view);
            item_id = item_view.findViewById((R.id.item_id_value));
            item_name = item_view.findViewById(R.id.item_name_card_value);
            quantity = item_view.findViewById(R.id.quantity_card_value);
            cost = item_view.findViewById(R.id.cost_card_value);
            description = item_view.findViewById(R.id.description_card_value);
            isfrozen = item_view.findViewById(R.id.isfrozen_card_value);
            delete_button = item_view.findViewById(R.id.delete_button_card);
        }
    }
}
