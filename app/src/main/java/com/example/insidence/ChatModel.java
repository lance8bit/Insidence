package com.example.insidence;

import java.util.ArrayList;

public class ChatModel {

    private String idIncidencia;
    private ArrayList<messageModel> mensajes;

    public ChatModel(String idIncidencia, ArrayList<messageModel> mensajes) {
        this.idIncidencia = idIncidencia;
        this.mensajes = mensajes;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public ArrayList<messageModel> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<messageModel> mensajes) {
        this.mensajes = mensajes;
    }
}
