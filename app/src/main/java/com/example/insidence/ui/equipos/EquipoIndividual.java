package com.example.insidence.ui.equipos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.insidence.Equipos;
import com.example.insidence.R;

import java.util.Map;

public class EquipoIndividual extends AppCompatActivity {

    private TextView nombreDispositivo, idDispositivo, ipDispositivo, estadoDispositivo, ramDispositivo, tGDispositivo, pBDispositivo, procesadorDispositivo, listaUsuariosDisp;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_individual);

        final Equipos equipo = (Equipos) getIntent().getSerializableExtra("equipo");

        nombreDispositivo = findViewById(R.id.deviceName);
        idDispositivo = findViewById(R.id.idDevice);
        ipDispositivo = findViewById(R.id.ipDevice);
        estadoDispositivo = findViewById(R.id.statusDevice);
        ramDispositivo = findViewById(R.id.ramDevice);
        tGDispositivo = findViewById(R.id.gfDevice);
        pBDispositivo = findViewById(R.id.motherboardDevice);
        procesadorDispositivo = findViewById(R.id.processorDevice);
        listaUsuariosDisp = findViewById(R.id.usersDevice);

        nombreDispositivo.setText(equipo.getName());
        idDispositivo.setText(equipo.getId());
        ipDispositivo.setText(equipo.getIp());

        if(equipo.isActive()){
            estadoDispositivo.setText("En línea");
        } else {
            estadoDispositivo.setText("Desconectado");
        }

        ramDispositivo.setText(equipo.getHardware().get("RAM"));
        tGDispositivo.setText(equipo.getHardware().get("graphicsCard"));
        pBDispositivo.setText(equipo.getHardware().get("motherboard"));
        procesadorDispositivo.setText(equipo.getHardware().get("processor"));

        String users = "";
        for (Map.Entry<String, String> entry : equipo.getUsers().entrySet()) {
            users += entry.getValue() + "\n";
        }

        listaUsuariosDisp.setText(users);

        goBack = findViewById(R.id.buttonBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}