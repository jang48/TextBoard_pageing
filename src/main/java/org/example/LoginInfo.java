package org.example;

public class LoginInfo {
    private String joinid;
    private String joinpw;
    private String joinname;

    LoginInfo(String joinid, String joinpw, String joinname){
        this.joinid = joinid;
        this.joinpw = joinpw;
        this.joinname = joinname;
    }
    public String getJoinid() {
        return joinid;
    }

    public String getJoinpw() {
        return joinpw;
    }

    public String getJoinname() {
        return joinname;
    }

    public void setJoinid(String joinid) {
        this.joinid = joinid;
    }

    public void setJoinpw(String joinpw) {
        this.joinpw = joinpw;
    }

    public void setJoinname(String joinname) {
        this.joinname = joinname;
    }
}
