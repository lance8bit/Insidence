package com.example.insidence;

public class User {
    public String idUser;
    public int rolUser;

    public User(String idUser, int rolUser) {
        this.idUser = idUser;
        this.rolUser = rolUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getRolUser() {
        return rolUser;
    }

    public void setRolUser(int rolUser) {
        this.rolUser = rolUser;
    }
}
