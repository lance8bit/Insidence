package com.example.insidence;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Map;

public class Equipos implements Serializable {
    private String id;
    private String name;
    private String ip;

    /*
     * Pirority:
     * 0 - Low
     * 1 - Medium
     * 2 - High
     */
    private int prioridad;
    private Map<String, String> users;
    private Map<String, String> hardware;
    private boolean active;

    public Equipos() {
    }

    public Equipos(String id, String name, String ip, Map<String, String> users, Map<String, String> hardware, boolean active, int prioridad) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.users = users;
        this.hardware = hardware;
        this.active = active;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }

    public Map<String, String> getHardware() {
        return hardware;
    }

    public void setHardware(Map<String, String> hardware) {
        this.hardware = hardware;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
