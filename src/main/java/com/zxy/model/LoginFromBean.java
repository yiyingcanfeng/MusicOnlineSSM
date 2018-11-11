package com.zxy.model;

public class LoginFromBean {
    private String name;
    private String password;
    private String code;
    private String rightCode;
    private String msg;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getRightCode() {
        return rightCode;
    }
    
    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    @Override
    public String toString() {
        return "LoginFromBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", rightCode='" + rightCode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
