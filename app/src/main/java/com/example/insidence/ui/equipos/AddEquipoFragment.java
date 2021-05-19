package com.example.insidence.ui.equipos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.insidence.Equipos;
import com.example.insidence.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddEquipoFragment extends Fragment {

    private EditText nombreEquipoEt, ipEquipoEt, ramEquipoEt, gpuEquipoEt, placaBaseEquipoEt, procesadorEquipoEt;
    private Spinner estadoEquipoSp, prioridadEquipoSp;
    private Button addEquipoBtn;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference refDevices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_equipo, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        refDevices = database.getReference("devices");

        String idEquipo = "DEV" + UUID.randomUUID().toString();

        nombreEquipoEt = root.findViewById(R.id.nameEquipoET);
        ipEquipoEt = root.findViewById(R.id.deviceIP_ET);
        ramEquipoEt = root.findViewById(R.id.deviceRAM_ET);
        gpuEquipoEt = root.findViewById(R.id.deviceGPU_ET);
        placaBaseEquipoEt = root.findViewById(R.id.deviceMB_ET);
        procesadorEquipoEt = root.findViewById(R.id.deviceProcessorET);
        estadoEquipoSp = root.findViewById(R.id.statusDeviceSp);
        prioridadEquipoSp = root.findViewById(R.id.prioridadEquipoSp);
        addEquipoBtn = root.findViewById(R.id.addEquipoBtn);

        addEquipoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean activeEq = false;
                int prioridadEq = 0;

                switch (estadoEquipoSp.getSelectedItem().toString()){
                    case "Activo":
                        activeEq = true;
                        break;
                    case "Inactivo":
                        activeEq = false;
                        break;
                }

                switch (prioridadEquipoSp.getSelectedItem().toString()){
                    case "Alta":
                        prioridadEq = 2;
                        break;
                    case "Media":
                        prioridadEq = 1;
                        break;
                    case "Baja":
                        prioridadEq = 0;
                        break;
                }

                Map<String, String> users = new HashMap<>();
                users.put("idUserEquipo", user.getUid());

                Map<String, String> hardware = new HashMap<>();
                hardware.put("RAM", ramEquipoEt.getText().toString());
                hardware.put("graphicsCard", gpuEquipoEt.getText().toString());
                hardware.put("motherboard", placaBaseEquipoEt.getText().toString());
                hardware.put("processor", procesadorEquipoEt.getText().toString());

                Equipos new_equipo = new Equipos(idEquipo, nombreEquipoEt.getText().toString(), ipEquipoEt.getText().toString(), users, hardware, activeEq, prioridadEq);
                refDevices.child(idEquipo).setValue(new_equipo);

                Toast.makeText(getContext(), "Equipo " + nombreEquipoEt.getText().toString() + " añadido correctamente", Toast.LENGTH_SHORT).show();

                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new EquiposFragment()).commit();
            }
        });

        return root;
    }
}