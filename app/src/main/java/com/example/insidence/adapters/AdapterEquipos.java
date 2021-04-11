package com.example.insidence.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.insidence.Equipos;
import com.example.insidence.R;
import com.example.insidence.ui.equipos.EquipoIndividual;

import java.util.ArrayList;

public class AdapterEquipos extends RecyclerView.Adapter<AdapterEquipos.MyViewHolder>{

    ArrayList<Equipos> mList;
    Context context;

    public AdapterEquipos(Context context, ArrayList<Equipos> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_equipo_recycler_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Equipos equipo = mList.get(position);
        holder.nameEquipo.setText(equipo.getName());
        holder.idEquipo.setText(equipo.getId());

        holder.card_block.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, EquipoIndividual.class);
                mIntent.putExtra("equipo", mList.get(position));
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameEquipo, idEquipo;
        CardView card_block;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameEquipo = itemView.findViewById(R.id.nameEquipo);
            idEquipo = itemView.findViewById(R.id.idEquipo);
            card_block = itemView.findViewById(R.id.cardview_cont);
        }
    }

}
