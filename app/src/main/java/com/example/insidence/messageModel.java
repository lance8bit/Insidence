package com.example.insidence;

public class messageModel {
    private int idMensaje;
    private String idSenderUser;
    private String cuerpoMensaje;
    private long timestamp;

    public messageModel(int idMensaje, String idSenderUser, String cuerpoMensaje, long timestamp) {
        this.idMensaje = idMensaje;
        this.idSenderUser = idSenderUser;
        this.cuerpoMensaje = cuerpoMensaje;
        this.timestamp = timestamp;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getIdSenderUser() {
        return idSenderUser;
    }

    public void setIdSenderUser(String idSenderUser) {
        this.idSenderUser = idSenderUser;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
