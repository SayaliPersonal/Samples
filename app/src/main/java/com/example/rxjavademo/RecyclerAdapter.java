package com.example.rxjavademo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Employee> employees = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context context, List<Employee> employees) {
        this.employees = employees;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        // Set the name of the 'NicePlace'
        ((ViewHolder)viewHolder).mName.setText(employees.get(i).getEmployeeName());

        // Set the image

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
