package com.example.insidence.ui.equipos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insidence.Equipos;
import com.example.insidence.adapters.AdapterEquipos;
import com.example.insidence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EquiposFragment extends Fragment {
    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference refDevices;
    private ArrayList<Equipos> arrayListEquipos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_equipos, container, false);
        recyclerView = root.findViewById(R.id.recyclerEquipos);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String uid = user.getUid();

            database = FirebaseDatabase.getInstance();
            refDevices = database.getReference("devices");

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);

            arrayListEquipos = new ArrayList<Equipos>();
            AdapterEquipos mainAdapter = new AdapterEquipos(getContext(), arrayListEquipos);

            recyclerView.setAdapter(mainAdapter);
            Log.i("CURID", uid);

            refDevices.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    arrayListEquipos.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Equipos equipo = dataSnapshot.getValue(Equipos.class);
                        if (equipo.getUsers().containsValue(uid)) {
                            arrayListEquipos.add(equipo);
                        }
                    }
                    mainAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        return root;
    }
}