package com.example.insidence.ui.incidencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.insidence.ChatModel;
import com.example.insidence.Incidencias;
import com.example.insidence.R;
import com.example.insidence.ui.chat.ChatFragment;

public class IncidenciaIndividual extends AppCompatActivity {

    private TextView userIdtv, techUserId, deviceIdtv, dateInctv, statusInctv, titleInctv, infoInctv;
    private Button goBackBtn, chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia_individual);

        final Incidencias incidencia = (Incidencias) getIntent().getSerializableExtra("incidencia");

        userIdtv = findViewById(R.id.userIdText);
        techUserId = findViewById(R.id.techUserId);
        deviceIdtv = findViewById(R.id.deviceIdText);
        dateInctv = findViewById(R.id.dateIncText);
        statusInctv = findViewById(R.id.statusText);
        titleInctv = findViewById(R.id.titleIncidenciaText);
        infoInctv = findViewById(R.id.infoIncidence);
        goBackBtn = findViewById(R.id.buttonBack);

        userIdtv.setText(incidencia.getEmpUser());
        techUserId.setText(incidencia.getTechUser());
        deviceIdtv.setText(incidencia.getIdDevice());
        dateInctv.setText("Date: "+incidencia.getDateIncidencia());
        titleInctv.setText(incidencia.getTitleIncidencia());
        infoInctv.setText(incidencia.getInfoIncidencia());

        switch (incidencia.getStatusIncidencia()){
            case 0:
                statusInctv.setText("Pendiente");
                break;
            case 1:
                statusInctv.setText("Abierta");
                break;
            case 2:
                statusInctv.setText("Cerrada");
                break;
        }

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("idUser", incidencia.getEmpUser());
        bundle.putString("idTech", incidencia.getTechUser());
        bundle.putString("idinc", incidencia.getTechUser());

        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_incidence, chatFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}