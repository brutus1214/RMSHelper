package com.jcsoftware.rmshelper;

/**
 * Created by brutu on 9/21/2016.
 * OK
 */

class LoginData {
    private String server;

    /* 0 is SQL auth, 1 is Windows auth */
    private Boolean auth;
    private String name;
    private String pwd;
    private String db;
    private Boolean remember;

    LoginData() {
		/* set to LAMSS */
        server = "10.10.9.200";
        auth = false;
        name = "sa";
        pwd = "L4Martss";
        db = "lamss";
        remember = true;
    }

    LoginData(String server, Boolean auth, String name, String pwd, String db) {
        this.server = server;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setdb(String db) {
        this.db = db;
    }

    public void setRemember(Boolean rem) {
        this.remember = rem;
    }


    String getServer() {
        return server;
    }
    public Boolean getAuth() {
        return auth;
    }
    public String getName() {
        return name;
    }
    String getPwd() {
        return pwd;
    }
    String getDb() {
        return db;
    }
    public Boolean getRemember() {
        return remember;
    }
}
