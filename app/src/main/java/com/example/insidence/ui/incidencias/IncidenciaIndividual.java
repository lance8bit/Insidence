package com.example.insidence.ui.incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.insidence.Incidencias;
import com.example.insidence.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class IncidenciaIndividual extends AppCompatActivity {

    private TextView userIdtv, techUserId, deviceIdtv, dateInctv, statusInctv, titleInctv, infoInctv;
    private Button goBackBtn, revButton, solvButton;
    FirebaseDatabase database;
    DatabaseReference refInc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia_individual);
        database = FirebaseDatabase.getInstance();
        refInc = database.getReference("incidences");

        final Incidencias incidencia = (Incidencias) getIntent().getSerializableExtra("incidencia");

        userIdtv = findViewById(R.id.userIdText);
        techUserId = findViewById(R.id.techUserId);
        deviceIdtv = findViewById(R.id.deviceIdText);
        dateInctv = findViewById(R.id.dateIncText);
        statusInctv = findViewById(R.id.statusText);
        titleInctv = findViewById(R.id.titleIncidenciaText);
        infoInctv = findViewById(R.id.infoIncidence);
        goBackBtn = findViewById(R.id.buttonBack);
        revButton = findViewById(R.id.btnRevision);
        solvButton = findViewById(R.id.buttonSolv);

        userIdtv.setText(incidencia.getEmpUser());
        techUserId.setText(incidencia.getTechUser());
        deviceIdtv.setText(incidencia.getIdDevice());

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Integer.valueOf(incidencia.getDateIncidencia()) * 1000L);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();

        dateInctv.setText("Date: "+date);
        titleInctv.setText(incidencia.getTitleIncidencia());
        infoInctv.setText(incidencia.getInfoIncidencia());

        switch (incidencia.getStatusIncidencia()){
            case 1:
                revButton.setVisibility(View.GONE);
                break;
            case 2:
                revButton.setVisibility(View.GONE);
                solvButton.setVisibility(View.GONE);
                break;
        }

        revButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HEYYOU", "onClick: Enters rev Button");
                refInc.child(incidencia.getIdIncidencia()).child("statusIncidencia").setValue(1);
                statusInctv.setText("Pendiente");
                revButton.setVisibility(View.GONE);
            }
        });

        solvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refInc.child(incidencia.getIdIncidencia()).child("statusIncidencia").setValue(2);
                statusInctv.setText("Solucionada");
                revButton.setVisibility(View.GONE);
                solvButton.setVisibility(View.GONE);
            }
        });

        switch (incidencia.getStatusIncidencia()){
            case 0:
                statusInctv.setText("Abierta");
                break;
            case 1:
                statusInctv.setText("Pendiente");
                break;
            case 2:
                statusInctv.setText("Solucionada");
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


    }
}