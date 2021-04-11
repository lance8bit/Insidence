package com.example.insidence.ui.incidencias;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insidence.Incidencias;
import com.example.insidence.R;
import com.example.insidence.adapters.AdapterIncidencias;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IncidenciasFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference refIncidencias;
    private ArrayList<Incidencias> arrayListIncidencias;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_incidencias, container, false);
        recyclerView = root.findViewById(R.id.recyclerIncidencias);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            String uid = user.getUid();

            database = FirebaseDatabase.getInstance();
            refIncidencias = database.getReference("incidences");

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);

            arrayListIncidencias = new ArrayList<Incidencias>();
            AdapterIncidencias mainAdapter = new AdapterIncidencias(getContext(), arrayListIncidencias);

            recyclerView.setAdapter(mainAdapter);
            Log.i("CURID", uid);

            refIncidencias.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Incidencias incidencia = dataSnapshot.getValue(Incidencias.class);
                        if (incidencia.getEmpUser().contains(uid)) {
                            arrayListIncidencias.add(incidencia);
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