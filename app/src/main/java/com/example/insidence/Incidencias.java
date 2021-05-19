package com.example.insidence;

import java.io.Serializable;

public class Incidencias implements Serializable {
    private String idIncidencia;
    private String idDevice;
    private String titleIncidencia;
    private String dateIncidencia;

    /*
     * Status:
     * 0 - Pendiente
     * 1 - En revisión
     * 2 - Solucionada
     */
    private int statusIncidencia;

    /*
     * Pirority:
     * 0 - Low
     * 1 - Medium
     * 2 - High
     */
    private int priorityIncidencia;
    private String empUser, techUser;
    private String infoIncidencia;

    public Incidencias() {
    }

    public Incidencias(String idIncidencia, String idDevice, String titleIncidencia, String dateIncidencia, int statusIncidencia, int priorityIncidencia, String empUser, String techUser, String infoIncidencia) {
        this.idIncidencia = idIncidencia;
        this.idDevice = idDevice;
        this.titleIncidencia = titleIncidencia;
        this.dateIncidencia = dateIncidencia;
        this.statusIncidencia = statusIncidencia;
        this.priorityIncidencia = priorityIncidencia;
        this.empUser = empUser;
        this.techUser = techUser;
        this.infoIncidencia = infoIncidencia;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTitleIncidencia() {
        return titleIncidencia;
    }

    public void setTitleIncidencia(String titleIncidencia) {
        this.titleIncidencia = titleIncidencia;
    }

    public String getDateIncidencia() {
        return dateIncidencia;
    }

    public void setDateIncidencia(String dateIncidencia) {
        this.dateIncidencia = dateIncidencia;
    }

    public int getStatusIncidencia() {
        return statusIncidencia;
    }

    public void setStatusIncidencia(int statusIncidencia) {
        this.statusIncidencia = statusIncidencia;
    }

    public int getPriorityIncidencia() {
        return priorityIncidencia;
    }

    public void setPriorityIncidencia(int priorityIncidencia) {
        this.priorityIncidencia = priorityIncidencia;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getEmpUser() {
        return empUser;
    }

    public void setEmpUser(String empUser) {
        this.empUser = empUser;
    }

    public String getTechUser() {
        return techUser;
    }

    public void setTechUser(String techUser) {
        this.techUser = techUser;
    }

    public String getInfoIncidencia() {
        return infoIncidencia;
    }

    public void setInfoIncidencia(String infoIncidencia) {
        this.infoIncidencia = infoIncidencia;
    }
}
