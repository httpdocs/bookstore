package edu.scau.model;

public class User {
	
    private String userid;

    private String password;

    private String name;

    private Integer defaddr;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDefaddr() {
        return defaddr;
    }

    public void setDefaddr(Integer defaddr) {
        this.defaddr = defaddr;
    }
}