package com.kelompok1.portalpengaduanuser.modelapi;

public class ResponseLogin {
    private static String login;
    private static String status;

    public ResponseLogin(String login, String status) {
        this.login = login;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
