package com.example.lifelinetest.modules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifelinetest.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Patient> patients;
    private OnItemClicked onClick;

    public MyAdapter(Context c, ArrayList<Patient> p){
        context = c;
        patients = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(patients.get(position).name);
        holder.bloodType.setText(patients.get(position).bloodType);
        holder.imgView.setBackgroundResource(R.drawable.profile);
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(patients.get(position).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, bloodType;
        Button details;
        ImageView imgView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            bloodType = itemView.findViewById(R.id.blood_type);
            details = itemView.findViewById(R.id.details);
            imgView =  itemView.findViewById(R.id.profilePic);
        }
    }

    public interface OnItemClicked{
        void onItemClick(String position);
    }

    public void setOnClick(OnItemClicked onClick){
        this.onClick = onClick;
    }
}
