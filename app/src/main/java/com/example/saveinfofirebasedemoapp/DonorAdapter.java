package com.example.saveinfofirebasedemoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder> {
    private ArrayList<Donor> donorList;
    private Context context;

    public DonorAdapter(ArrayList<Donor> donorList, Context context) {
        this.donorList = donorList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View donorItemView = LayoutInflater.from(context).inflate(R.layout.single_donor_item_layout,parent,false);
        return new ViewHolder(donorItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Donor currentDonor = donorList.get(position);
        holder.donorNameTV.setText(currentDonor.getDonorName());
        holder.donorPhoneTV.setText(currentDonor.getDonorPhone());
        holder.donorLocationTV.setText(currentDonor.getDonorLocation());
        holder.donorEmailTV.setText(currentDonor.getDonorEmail());
        holder.donorBloodGroupTV.setText(currentDonor.getDonorBloodGroup());

    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView donorNameTV,donorPhoneTV,donorLocationTV,donorEmailTV,donorBloodGroupTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            donorNameTV = itemView.findViewById(R.id.donorNameTV);
            donorPhoneTV = itemView.findViewById(R.id.donorPhoneTV);
            donorLocationTV = itemView.findViewById(R.id.donorLocationTV);
            donorEmailTV = itemView.findViewById(R.id.donorEmailTV);
            donorBloodGroupTV = itemView.findViewById(R.id.donorBloodGroupTV);
        }
    }
}
