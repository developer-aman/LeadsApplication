package com.agtechnosolution.leadsapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AnujPc on 07-01-2019.
 */

public class LeadsRecyclerAdapter extends RecyclerView.Adapter<LeadsRecyclerAdapter.customViewHolder>{

    private ArrayList<Lead> leadsList;

    public LeadsRecyclerAdapter(ArrayList<Lead> leadsList) {
        this.leadsList = leadsList;
    }

    @Override
    public customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.item_layout,parent,false);
        return new customViewHolder(v);
    }

    @Override
    public void onBindViewHolder(customViewHolder holder, int position) {
        holder.name.setText(leadsList.get(position).getName());
        holder.email.setText(leadsList.get(position).getEmail());
        holder.mobile.setText(leadsList.get(position).getMobile());
        holder.comment.setText(leadsList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return leadsList.size();
    }

    public class customViewHolder extends RecyclerView.ViewHolder{

        private TextView name,email,mobile,comment;

        public customViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name_tv);
            email= itemView.findViewById(R.id.email_tv);
            mobile=itemView.findViewById(R.id.mobile_tv);
            comment=itemView.findViewById(R.id.comment_tv);
        }
    }

}
