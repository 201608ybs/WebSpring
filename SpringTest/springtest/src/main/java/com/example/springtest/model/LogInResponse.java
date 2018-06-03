package com.example.springtest.model;

public class LogInResponse {

    private String state;
    private String role;
    private boolean isSuccessful;

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
