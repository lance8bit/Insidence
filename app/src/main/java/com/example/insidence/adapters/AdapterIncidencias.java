package com.example.insidence.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insidence.Incidencias;
import com.example.insidence.R;
import com.example.insidence.ui.incidencias.IncidenciaIndividual;

import java.util.ArrayList;

public class AdapterIncidencias extends RecyclerView.Adapter<AdapterIncidencias.MyViewHolder>{

    ArrayList<Incidencias> mList;
    Context context;

    public AdapterIncidencias(Context context, ArrayList<Incidencias> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterIncidencias.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_incidencia_recycler_card, parent, false);
        return new AdapterIncidencias.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIncidencias.MyViewHolder holder, int position) {
        Incidencias incidencia = mList.get(position);
        holder.titleIncidencia.setText(incidencia.getTitleIncidencia());
        holder.infoIncidencia.setText(incidencia.getInfoIncidencia().substring(0, Math.min(incidencia.getInfoIncidencia().length(), 44)) + "...");
        holder.idUser.setText(incidencia.getEmpUser());

        switch (incidencia.getPriorityIncidencia()){
            case 0:
                holder.backgroundPriority.setBackgroundColor(Color.parseColor("#FFF033"));
                break;
            case 1:
                holder.backgroundPriority.setBackgroundColor(Color.parseColor("#FF9F33"));
                break;
            case 2:
                holder.backgroundPriority.setBackgroundColor(Color.parseColor("#FF3333"));
                break;
        }


        holder.card_block.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, IncidenciaIndividual.class);
                mIntent.putExtra("incidencia", incidencia);
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleIncidencia, infoIncidencia, idUser;
        CardView card_block;
        LinearLayout backgroundPriority;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundPriority = itemView.findViewById(R.id.backgroundPriority);
            titleIncidencia = itemView.findViewById(R.id.titleIncidencia);
            infoIncidencia = itemView.findViewById(R.id.descIndicidencia);
            idUser = itemView.findViewById(R.id.userId);
            card_block = itemView.findViewById(R.id.cardview_cont);
        }
    }

}
