package com.example.insidence.ui.incidencias;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.insidence.DashboardActivity;
import com.example.insidence.Equipos;
import com.example.insidence.Incidencias;
import com.example.insidence.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddIncidenciaFragment extends Fragment {

    private TextView idIncidencia_tv;
    private Spinner dispositivos_incidencia;
    private TextInputLayout asunto_incidencia_et, mensaje_incidencia_et;
    private Button add_incidencia_btn;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference refIncidencias, refDevices;
    private List<Equipos> equiposArrayList;

    public AddIncidenciaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_incidencia, container, false);

        ((DashboardActivity)getActivity()).getSupportActionBar().setTitle("Informar Incidencia");

        mAuth = FirebaseAuth.getInstance();

        equiposArrayList = new ArrayList<>();

        idIncidencia_tv = view.findViewById(R.id.viewIdIncidencia);
        dispositivos_incidencia = view.findViewById(R.id.spinner_devices);
        asunto_incidencia_et = view.findViewById(R.id.text_input_asunto);
        mensaje_incidencia_et = view.findViewById(R.id.text_input_mensaje);
        add_incidencia_btn = view.findViewById(R.id.btn_add_incidencia);

        String idIncidencia = "INC-" + UUID.randomUUID().toString();
        idIncidencia_tv.setText(idIncidencia);

        database = FirebaseDatabase.getInstance();
        refIncidencias = database.getReference("incidences");
        refDevices = database.getReference("devices");

        refDevices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Equipos equipo = dataSnapshot.getValue(Equipos.class);
                    if (equipo.getUsers().containsValue(mAuth.getCurrentUser().getUid())) {
                        equiposArrayList.add(equipo);
                    }
                }

                ArrayAdapter<Equipos> adapter = new ArrayAdapter<Equipos>(view.getContext(),
                        android.R.layout.simple_spinner_item, equiposArrayList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dispositivos_incidencia.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add_incidencia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipos equipo_selected = (Equipos) dispositivos_incidencia.getSelectedItem();
                Long timestamp = System.currentTimeMillis()/1000;
                int prioridadIncidencia = 0;
                switch (equipo_selected.getPrioridad()){
                    case 0:
                        prioridadIncidencia = 0;
                        break;
                    case 1:
                        prioridadIncidencia = 1;
                        break;
                    case 2:
                        prioridadIncidencia = 2;
                        break;
                }
                Incidencias incidencia = new Incidencias(idIncidencia, equipo_selected.getId(), asunto_incidencia_et.getEditText().getText().toString(), timestamp.toString(), 0, prioridadIncidencia, mAuth.getCurrentUser().getUid(), "s57rSy178igGctuP9thjHoXBQM03", mensaje_incidencia_et.getEditText().getText().toString());
                refIncidencias.child(idIncidencia).setValue(incidencia);

                Fragment incidencias = new IncidenciasFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, incidencias)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}